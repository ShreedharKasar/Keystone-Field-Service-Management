import React from 'react';
import { NavLink } from 'react-router-dom';
import {
    LayoutDashboard,
    ClipboardList,
    Users,
    Wrench,
    BarChart3,
    Settings,
    Building2
} from 'lucide-react';

const Sidebar = ({ role, closeSidebar }) => {

    const menuItems = {
        ADMIN: [
            { name: 'Dashboard', path: '/admin/dashboard', icon: LayoutDashboard },
            { name: 'Work Orders', path: '/admin/work-orders', icon: ClipboardList },
            { name: 'Customers', path: '/admin/customers', icon: Users },
            { name: 'Technicians', path: '/admin/technicians', icon: Wrench },
            { name: 'Reports', path: '/admin/reports', icon: BarChart3 },
            { name: 'Settings', path: '/admin/settings', icon: Settings }
        ],

        MANAGER: [
            { name: 'Dashboard', path: '/manager/dashboard', icon: LayoutDashboard },
            { name: 'Work Orders', path: '/manager/work-orders', icon: ClipboardList },
            { name: 'Technicians', path: '/manager/technicians', icon: Wrench },
            { name: 'Reports', path: '/manager/reports', icon: BarChart3 }
        ],

        DISPATCHER: [
            { name: 'Dashboard', path: '/dispatcher/dashboard', icon: LayoutDashboard },
            { name: 'Work Orders', path: '/dispatcher/work-orders', icon: ClipboardList },
            { name: 'Technicians', path: '/dispatcher/technicians', icon: Wrench }
        ],

        TECHNICIAN: [
            { name: 'Dashboard', path: '/technician/dashboard', icon: LayoutDashboard },
            { name: 'My Work Orders', path: '/technician/work-orders', icon: ClipboardList }
        ],

        CUSTOMER: [
            { name: 'Dashboard', path: '/customer/dashboard', icon: LayoutDashboard },
            { name: 'My Requests', path: '/customer/requests', icon: ClipboardList }
        ]
    };

    const items = menuItems[role] || [];

    return (
        <aside className="sidebar">

            <div className="sidebar-brand">
                <Building2 size={28} />
                <span>KEYSTONE</span>
            </div>

            <div className="sidebar-menu">
                <p className="menu-heading">MAIN MENU</p>

                {items.map((item) => {
                    const Icon = item.icon;

                    return (
                        <NavLink
                            key={item.path}
                            to={item.path}
                            className={({ isActive }) =>
                                isActive ? 'sidebar-link active' : 'sidebar-link'
                            }
                            onClick={closeSidebar}
                        >
                            <Icon size={19} />
                            <span>{item.name}</span>
                        </NavLink>
                    );
                })}

            </div>

        </aside>
    );
};

export default Sidebar;

