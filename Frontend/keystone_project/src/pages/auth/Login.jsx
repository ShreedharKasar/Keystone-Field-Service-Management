import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { loginUser } from '../../Service/authService';
import { toast } from 'react-toastify';

function Login() {

const navigate = useNavigate();

const [userEmail, setUserEmail] = useState('');
const [password, setPassword] = useState('');
const [loading, setLoading] = useState(false);

const handleLogin = async (e) => {
    e.preventDefault();

    try {
        setLoading(true);

        const userData = {
            userEmail,
            password
        };

        const response = await loginUser(userData);

        // Validate the backend response
        if (!response.token || !response.role) {
            toast.error('Invalid login response from server');
            return;
        }

        // Store JWT
        sessionStorage.setItem('token', response.token);

        // Store user information
        sessionStorage.setItem('userEmail', response.userEmail);
        sessionStorage.setItem('role', response.role);

        toast.success('Login successful!');

        // Role-based navigation
        const roleRoutes = {
            ADMIN: '/admin/dashboard',
            MANAGER: '/manager/dashboard',
            DISPATCHER: '/dispatcher/dashboard',
            TECHNICIAN: '/technician/dashboard',
            CUSTOMER: '/customer/dashboard'
        };

        const dashboard = roleRoutes[response.role];

        if (dashboard) {
            navigate(dashboard, { replace: true });
        } else {
            sessionStorage.clear();
            toast.error('Invalid user role');
        }

    } catch (error) {

        toast.error(
            error.response?.data?.message || 'Login failed'
        );

    } finally {
        setLoading(false);
    }
};

return (
    <div className="auth-container">
        <div className="auth-card">

            <h2 className="text-center">Keystone</h2>

            <p className="text-center text-muted">
                Field Service Management Platform
            </p>

            <h4 className="mt-4 mb-3">Welcome Back!</h4>

            <form onSubmit={handleLogin}>

                <div className="mb-3">
                    <label>Email Address</label>

                    <input
                        type="email"
                        className="form-control"
                        placeholder="Enter your email"
                        value={userEmail}
                        onChange={(e) => setUserEmail(e.target.value)}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label>Password</label>

                    <input
                        type="password"
                        className="form-control"
                        placeholder="Enter your password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>

                <div className="text-end mb-3">
                    <Link to="/forgot-password">
                        Forgot Password?
                    </Link>
                </div>

                <button
                    type="submit"
                    className="btn btn-primary w-100"
                    disabled={loading}
                >
                    {loading ? 'Logging in...' : 'Login'}
                </button>

            </form>

            <p className="text-center mt-4">
                Don't have an account?

                <Link to="/register" className="ms-2">
                    Register
                </Link>
            </p>

        </div>
    </div>
);

}

export default Login;
