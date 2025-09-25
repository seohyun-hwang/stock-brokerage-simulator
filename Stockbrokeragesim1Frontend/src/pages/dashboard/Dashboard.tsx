import { useState } from "react";
import "./Dashboard.css";
import Form from "react-bootstrap/Form"
import Button from "react-bootstrap/Button";
import {useNavigate} from "react-router-dom";

const Dashboard = () => {

    const [formData, setFormData] = useState({
        month: "",
        day: "",
        year: ""
    })


    const handleInputChange = (event: { target: { name: any; value: any; }; }) => {
        const { name, value } = event.target;
        setFormData({
            ...formData,
            [name]: value
        })
    };

    const navigate = useNavigate();

    const handleSubmit = async (event: { preventDefault: () => void; }) => {
        event.preventDefault();

        console.log(formData);

        try {
            const response = await fetch("http://localhost:5173/api/stock-order", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(formData)
            });

            const data = await response.json();
            console.log("date processed:", data);
            navigate("/");
        } catch (error) {
            console.log("Error processing date:", error);
        }
    };

    return (
        <>
            <div className="center-form">
                <h1>Post New Employee</h1>
                <Form onSubmit={handleSubmit}>
                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="text"
                            name="month"
                            placeholder="MM"
                            value={formData.month}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="email"
                            name="day"
                            placeholder="DD"
                            value={formData.day}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="text"
                            name="year"
                            placeholder="YYYY"
                            value={formData.year}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Button variant="primary" type="submit">Submit Date</Button>
                </Form>
            </div>
        </>
    );
}

export default Dashboard;