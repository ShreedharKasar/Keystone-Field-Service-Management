import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';

const ProtectedRoute = ({ allowedRoles }) => {

    const token = sessionStorage.getItem('token');
    const role = sessionStorage.getItem('role');

    // Check whether user is logged in
    if (!token) {
        return <Navigate to="/login" replace />;
    }

    // Check whether user has permission
    if (allowedRoles && !allowedRoles.includes(role)) {
        return <Navigate to="/unauthorized" replace />;
    }

    // Render the nested route
    return <Outlet />;
};

export default ProtectedRoute;