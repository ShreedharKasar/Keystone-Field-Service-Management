import React, { useEffect, useState } from 'react';
import DashboardLayout from '../../components/layout/DashboardLayout';
import { getAllCustomers } from '../../Service/customerService';
import { getAllTechnicians } from '../../Service/technicianService';
import { getAllWorkOrders } from '../../Service/workOrderService';
import WorkOrderChart from '../../components/dashboard/WorkOrderChart';

const AdminDashboard = () => {

    const [totalCustomers, setTotalCustomers] = useState(0);
    const [loading, setLoading] = useState(true);
    const [totalTechnicians, setTotalTechnicians] = useState(0);
    const [totalWorkOrders, setTotalWorkOrders] = useState(0);
    const [pendingWorkOrders, setPendingWorkOrders] = useState(0);
    const [completedWorkOrders, setCompletedWorkOrders] = useState(0);
    const [assignedWorkOrders, setAssignedWorkOrders] = useState(0);
    const [inProgressWorkOrders, setInProgressWorkOrders] = useState(0);
    const [onHoldWorkOrders, setOnHoldWorkOrders] = useState(0);
    const [closedWorkOrders, setClosedWorkOrders] = useState(0);
    const [cancelledWorkOrders, setCancelledWorkOrders] = useState(0);
    const [workOrders, setWorkOrders] = useState([]);

    useEffect(() => {
        fetchCustomers();
        fetchTechnicians();
        fetchWorkOrders();
    }, []);

    const fetchTechnicians = async () => {
    try {
        const data = await getAllTechnicians();

        console.log("Technician API Response:", data);
        console.log("Technician Count:", data.length);

        setTotalTechnicians(data.length);

    } catch (error) {
        console.error("Technician API Error:", error);
        console.error("Status:", error.response?.status);
        console.error("Response:", error.response?.data);
    }
};

   const fetchWorkOrders = async () => {
    try {
        const workOrders = await getAllWorkOrders();

        setWorkOrders(workOrders);
        setTotalWorkOrders(workOrders.length);

        setPendingWorkOrders(
            workOrders.filter(order => order.workStatus === 'NEW').length
        );

        setAssignedWorkOrders(
            workOrders.filter(order => order.workStatus === 'ASSIGNED').length
        );

        setInProgressWorkOrders(
            workOrders.filter(order => order.workStatus === 'IN_PROGRESS').length
        );

        setOnHoldWorkOrders(
            workOrders.filter(order => order.workStatus === 'ON_HOLD').length
        );

        setCompletedWorkOrders(
            workOrders.filter(order => order.workStatus === 'COMPLETED').length
        );

        setClosedWorkOrders(
            workOrders.filter(order => order.workStatus === 'CLOSED').length
        );

        setCancelledWorkOrders(
            workOrders.filter(order => order.workStatus === 'CANCELLED').length
        );

    } catch (error) {
        console.error("Error fetching work orders:", error);
    }
};

   const fetchCustomers = async () => {
    try {
        const data = await getAllCustomers();

        console.log("Customer API Response:", data);
        console.log("Customer Count:", data.length);

        setTotalCustomers(data.length);

    } catch (error) {
        console.error("Customer API Error:", error);
        console.error("Status:", error.response?.status);
        console.error("Response:", error.response?.data);
    } finally {
        setLoading(false);
    }
};

    return (
        <DashboardLayout>

            <h2>Admin Dashboard</h2>
            <p>Welcome to Keystone Admin Panel!</p>

            <div className="row mt-4">

               <div className="col-md-4 mb-3">
                    <div className="card p-4 shadow-sm">
                        <h4>Work Orders Statistics</h4>
                         <hr />

                        <p>Total Work Orders: <strong>{totalWorkOrders}</strong></p>
                        <p>New: <strong>{pendingWorkOrders}</strong></p>
                        <p>Assigned: <strong>{assignedWorkOrders}</strong></p>
                        <p>In Progress: <strong>{inProgressWorkOrders}</strong></p>
                        <p>On Hold: <strong>{onHoldWorkOrders}</strong></p>
                        <p>Completed: <strong>{completedWorkOrders}</strong></p>
                        <p>Closed: <strong>{closedWorkOrders}</strong></p>
                        <p>Cancelled: <strong>{cancelledWorkOrders}</strong></p>
                    </div>
                </div>

                <div className="col-md-4 mb-3">
                    <div className="card p-4 shadow-sm">
                        <h4>Total Customers</h4>

                        <h2>
                            {loading ? 'Loading...' : totalCustomers}
                        </h2>

                    </div>
                </div>

                <div className="col-md-4 mb-3">
                   <div className="card p-4 shadow-sm">
                        <h4>Total Technicians</h4>
                        <h2>{totalTechnicians}</h2>
                    </div>
                </div>
            </div>

            <div className="row mt-4">
            <div className="col-md-6 mb-3">
                <WorkOrderChart
                    pending={pendingWorkOrders}
                    assigned={assignedWorkOrders}
                    inProgress={inProgressWorkOrders}
                    onHold={onHoldWorkOrders}
                    completed={completedWorkOrders}
                    closed={closedWorkOrders}
                    cancelled={cancelledWorkOrders}
                />
            </div>

            {/* Recent Work Orders */}

<div className="row mt-4">
    <div className="col-12">

        <div className="card p-4 shadow-sm">

            <h4>Recent Work Orders</h4>
            <hr />

            <div className="table-responsive">

                <table className="table table-hover table-bordered">

                    <thead className="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Customer ID</th>
                            <th>Technician ID</th>
                            <th>Priority</th>
                            <th>Status</th>
                        </tr>
                    </thead>

                    <tbody>

                        {workOrders.length === 0 ? (

                            <tr>
                                <td colSpan="6" className="text-center">
                                    No Work Orders Available
                                </td>
                            </tr>

                        ) : (

                            [...workOrders]
                                .sort((a, b) =>
                                    new Date(b.createdAt) - new Date(a.createdAt)
                                )
                                .slice(0, 5)
                                .map(order => (

                                    <tr key={order.id}>

                                        <td>{order.id}</td>

                                        <td>{order.title}</td>

                                        <td>{order.customerId}</td>

                                        <td>{order.technicianId}</td>

                                        <td>
                                            <span className="badge bg-warning text-dark">
                                                {order.priority}
                                            </span>
                                        </td>

                                        <td>
                                            <span className="badge bg-primary">
                                                {order.workStatus}
                                            </span>
                                        </td>

                                    </tr>

                                ))

                        )}

                    </tbody>

                </table>

            </div>

        </div>

    </div>
</div>
        </div>

        </DashboardLayout>
    );
};

export default AdminDashboard;