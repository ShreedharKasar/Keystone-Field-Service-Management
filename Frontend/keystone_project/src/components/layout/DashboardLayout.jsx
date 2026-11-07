import React, { useState } from 'react';
import Sidebar from './Sidebar';
import Navbar from './Navbar';
import '../../styles/dashboard.css';

const DashboardLayout = ({ children }) => {

    const role = sessionStorage.getItem('role');

    const [sidebarOpen, setSidebarOpen] = useState(false);

    const toggleSidebar = () => {
        setSidebarOpen(!sidebarOpen);
    };

    const closeSidebar = () => {
        setSidebarOpen(false);
    };

    return (
        <div className="dashboard-container">

            {sidebarOpen && (
                <div
                    className="sidebar-overlay"
                    onClick={closeSidebar}
                />
            )}

            <div className={sidebarOpen ? 'sidebar-wrapper open' : 'sidebar-wrapper'}>
                <Sidebar
                    role={role}
                    closeSidebar={closeSidebar}
                />
            </div>

            <div className="dashboard-main">

                <Navbar toggleSidebar={toggleSidebar} />

                <main className="dashboard-content">
                    {children}
                </main>

            </div>

        </div>
    );
};

export default DashboardLayout;

