import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../../Service/api";


const Signup = () => {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        userEmail: "",
        password: "",
        phone: "",
        role: "CUSTOMER"
    });

    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    const handleChange = (e) => {

        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        setMessage("");
        setError("");

        try {

            const response = await api.post(
                "/api/auth/register",
                formData
            );

            setMessage(
                response.data.message || "Registration successful!"
            );

            setTimeout(() => {
                navigate("/login");
            }, 1000);

        } catch (err) {

            setError(
                err.response?.data?.message ||
                "Registration failed. Please try again."
            );

        }
    };

    return (
        <div className="auth-container">

            <div className="auth-card">

                <h1>Keystone</h1>

                <h2>Create Account</h2>

                <p className="auth-subtitle">
                    Create your Keystone account
                </p>

                <form onSubmit={handleSubmit}>

                    <div className="name-row">

                        <div className="input-group">

                            <label>First Name</label>

                            <input
                                type="text"
                                name="firstName"
                                value={formData.firstName}
                                onChange={handleChange}
                                placeholder="First name"
                                required
                            />

                        </div>

                        <div className="input-group">

                            <label>Last Name</label>

                            <input
                                type="text"
                                name="lastName"
                                value={formData.lastName}
                                onChange={handleChange}
                                placeholder="Last name"
                                required
                            />

                        </div>

                    </div>

                    <div className="input-group">

                        <label>Email</label>

                        <input
                            type="email"
                            name="userEmail"
                            value={formData.userEmail}
                            onChange={handleChange}
                            placeholder="Enter your email"
                            required
                        />

                    </div>

                    <div className="input-group">

                        <label>Phone</label>

                        <input
                            type="tel"
                            name="phone"
                            value={formData.phone}
                            onChange={handleChange}
                            placeholder="Enter your phone number"
                            required
                        />

                    </div>

                    <div className="input-group">

                        <label>Password</label>

                        <input
                            type="password"
                            name="password"
                            value={formData.password}
                            onChange={handleChange}
                            placeholder="Create a password"
                            required
                        />

                    </div>

                    <div className="input-group">

                        <label>Role</label>

                        <select
                            name="role"
                            value={formData.role}
                            onChange={handleChange}
                        >

                            <option value="CUSTOMER">
                                Customer
                            </option>

                            <option value="TECHNICIAN">
                                Technician
                            </option>

                            <option value="DISPATCHER">
                                Dispatcher
                            </option>

                            <option value="MANAGER">
                                Manager
                            </option>

                            <option value="ADMIN">
                                Admin
                            </option>

                        </select>

                    </div>

                    {message && (
                        <p className="success-message">
                            {message}
                        </p>
                    )}

                    {error && (
                        <p className="error-message">
                            {error}
                        </p>
                    )}

                    <button
                        type="submit"
                        className="auth-button"
                    >
                        Sign Up
                    </button>

                </form>

                <p className="switch-auth">

                    Already have an account?

                    <Link to="/login">
                        Login
                    </Link>

                </p>

            </div>

        </div>
    );
};

export default Signup;

