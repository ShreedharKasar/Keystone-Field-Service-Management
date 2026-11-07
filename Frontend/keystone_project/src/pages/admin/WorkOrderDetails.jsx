import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

import DashboardLayout from '../../components/layout/DashboardLayout';
import { getWorkOrderById } from '../../Service/workOrderService';

const WorkOrderDetails = () => {

    // Get ID from URL
    const { id } = useParams();

    // Navigation
    const navigate = useNavigate();

    // State variables
    const [workOrder, setWorkOrder] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    // Fetch work order details
    useEffect(() => {

        const fetchWorkOrder = async () => {

            try {
                const data = await getWorkOrderById(id);

                console.log("Work Order Details:", data);

                setWorkOrder(data);

            } catch (error) {

                console.error("Error fetching work order:", error);

                setError(
                    error.response?.data?.message ||
                    'Unable to fetch work order details.'
                );

            } finally {
                setLoading(false);
            }
        };

        fetchWorkOrder();

    }, [id]);

    return (
        <DashboardLayout>

            <div className="container-fluid">

                <div className="d-flex justify-content-between align-items-center mb-4">

                    <h2>Work Order Details</h2>

                    <button
                        className="btn btn-secondary"
                        onClick={() => navigate('/admin/dashboard')}
                    >
                        Back to Dashboard
                    </button>

                </div>

                {loading && <h5>Loading work order details...</h5>}

                {!loading && error && (
                    <div className="alert alert-danger">
                        {error}
                    </div>
                )}

                {!loading && workOrder && (

                    <div className="card shadow-sm p-4">

                        <h4 className="mb-3">
                            Work Order #{workOrder.id}
                        </h4>

                        <hr />

                        <div className="row">

                            <div className="col-md-6 mb-3">
                                <strong>Title:</strong>
                                <p>{workOrder.title || 'N/A'}</p>
                            </div>

                            <div className="col-md-6 mb-3">
                                <strong>Priority:</strong>
                                <p>{workOrder.priority || 'N/A'}</p>
                            </div>

                            <div className="col-md-6 mb-3">
                                <strong>Status:</strong>
                                <p>{workOrder.workStatus || 'N/A'}</p>
                            </div>

                            <div className="col-md-6 mb-3">
                                <strong>Customer ID:</strong>
                                <p>{workOrder.customerId ?? 'N/A'}</p>
                            </div>

                            <div className="col-md-6 mb-3">
                                <strong>Site ID:</strong>
                                <p>{workOrder.siteId ?? 'N/A'}</p>
                            </div>

                            <div className="col-md-6 mb-3">
                                <strong>Technician ID:</strong>
                                <p>{workOrder.technicianId ?? 'N/A'}</p>
                            </div>

                            <div className="col-md-12 mb-3">
                                <strong>Description:</strong>
                                <p>{workOrder.description || 'N/A'}</p>
                            </div>

                            <div className="col-md-6 mb-3">
                                <strong>Created At:</strong>
                                <p>{workOrder.createdAt || 'N/A'}</p>
                            </div>

                            <div className="col-md-6 mb-3">
                                <strong>SLA Due Date:</strong>
                                <p>{workOrder.slaDueDate || 'N/A'}</p>
                            </div>

                            <div className="col-md-6 mb-3">
                                <strong>Updated At:</strong>
                                <p>{workOrder.updatedAt || 'N/A'}</p>
                            </div>

                        </div>

                    </div>

                )}

            </div>

        </DashboardLayout>
    );
};

export default WorkOrderDetails;