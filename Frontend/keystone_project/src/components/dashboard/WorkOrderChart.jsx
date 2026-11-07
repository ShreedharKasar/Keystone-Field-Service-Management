import React from 'react';
import {
PieChart,
Pie,
Cell,
Tooltip,
Legend,
ResponsiveContainer
} from 'recharts';

const WorkOrderChart = ({
pending,
assigned,
inProgress,
onHold,
completed,
closed,
cancelled
}) => {

const data = [
    { name: 'New', value: pending },
    { name: 'Assigned', value: assigned },
    { name: 'In Progress', value: inProgress },
    { name: 'On Hold', value: onHold },
    { name: 'Completed', value: completed },
    { name: 'Closed', value: closed },
    { name: 'Cancelled', value: cancelled }
];

const COLORS = [
    '#3498db',
    '#9b59b6',
    '#f39c12',
    '#e67e22',
    '#2ecc71',
    '#34495e',
    '#e74c3c'
];

const total = data.reduce((sum, item) => sum + item.value, 0);

return (
    <div className="card p-4 shadow-sm">
        <h5>Work Orders by Status</h5>
        <hr />

        {total === 0 ? (
            <div className="d-flex justify-content-center align-items-center"
                 style={{ height: '300px' }}>
                <p className="text-muted">
                    No work orders available yet.
                </p>
            </div>
        ) : (
            <ResponsiveContainer width="100%" height={350}>
                <PieChart>
                    <Pie
                        data={data.filter(item => item.value > 0)}
                        dataKey="value"
                        nameKey="name"
                        cx="50%"
                        cy="50%"
                        outerRadius={110}
                        label
                    >
                        {data.filter(item => item.value > 0).map((entry) => (
                            <Cell
                                key={entry.name}
                                fill={COLORS[data.findIndex(item => item.name === entry.name)]}
                            />
                        ))}
                    </Pie>

                    <Tooltip />
                    <Legend />
                </PieChart>
            </ResponsiveContainer>
        )}
    </div>
);

};

export default WorkOrderChart;
