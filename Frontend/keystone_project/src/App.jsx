import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';

import Login from './pages/auth/Login';
import Signup from './pages/auth/Signup';

import ProtectedRoute from './components/ProtectedRoute';

import AdminDashboard from './pages/dashboards/AdminDashboard';
import ManagerDashboard from './pages/dashboards/ManagerDashboard';
import DispatcherDashboard from './pages/dashboards/DispatcherDashboard';
import TechnicianDashboard from './pages/dashboards/TechnicianDashboard';
import CustomerDashboard from './pages/dashboards/CustomerDashboard';
import WorkOrderDetails from './pages/admin/WorkOrderDetails';

function App() {
    return (
        <BrowserRouter>
            <Routes>

                {/* Public Routes */}
                <Route path="/" element={<Navigate to="/login" replace />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Signup />} />

                {/* Admin Routes */}
                <Route element={<ProtectedRoute allowedRoles={['ADMIN']} />}>
                <Route path="/admin/dashboard" element={<AdminDashboard />}/>
                <Route path="/admin/work-orders/:id" element={<WorkOrderDetails />}/>
                </Route>

                {/* Manager Route */}
                <Route element={<ProtectedRoute allowedRoles={['MANAGER']} />}>
                    <Route path="/manager/dashboard" element={<ManagerDashboard />} />
                </Route>

                {/* Dispatcher Route */}
                <Route element={<ProtectedRoute allowedRoles={['DISPATCHER']} />}>
                    <Route path="/dispatcher/dashboard" element={<DispatcherDashboard />} />
                </Route>

                {/* Technician Route */}
                <Route element={<ProtectedRoute allowedRoles={['TECHNICIAN']} />}>
                    <Route path="/technician/dashboard" element={<TechnicianDashboard />} />
                </Route>

                {/* Customer Route */}
                <Route element={<ProtectedRoute allowedRoles={['CUSTOMER']} />}>
                    <Route path="/customer/dashboard" element={<CustomerDashboard />} />
                </Route>

                {/* Unauthorized */}
                <Route path="/unauthorized" element={<h2>403 - Access Denied</h2>} />

                {/* Unknown Routes */}
                <Route path="*" element={<h2>404 - Page Not Found</h2>} />

            </Routes>
        </BrowserRouter>
    );
}

export default App;