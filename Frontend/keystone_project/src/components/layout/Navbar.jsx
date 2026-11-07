import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Bell, LogOut, Menu, UserCircle } from 'lucide-react';
import { toast } from 'react-toastify';

const Navbar = ({ toggleSidebar }) => {

    const navigate = useNavigate();

    const userEmail = sessionStorage.getItem('userEmail');
    const role = sessionStorage.getItem('role');

    const handleLogout = () => {

        // Clear local authentication data
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('userEmail');
        sessionStorage.removeItem('role');

        toast.success('Logged out successfully!');

        navigate('/login', { replace: true });
    };

    return (
        <nav className="top-navbar">

            <button
                className="menu-toggle"
                onClick={toggleSidebar}
            >
                <Menu size={23} />
            </button>

            <div className="navbar-right">

                <button className="notification-button">
                    <Bell size={21} />
                </button>

                <div className="user-info">
                    <UserCircle size={30} />

                    <div>
                        <strong>{userEmail}</strong>
                        <small>{role}</small>
                    </div>
                </div>

                <button
                    className="logout-button"
                    onClick={handleLogout}
                >
                    <LogOut size={19} />
                    Logout
                </button>

            </div>

        </nav>
    );
};

export default Navbar;

