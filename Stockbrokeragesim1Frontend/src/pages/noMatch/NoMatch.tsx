import { useState } from "react";
import "./NoMatch.css";
import Form from "react-bootstrap/Form"
import Button from "react-bootstrap/Button";
import {useNavigate} from "react-router-dom";

const NoMatch = () => {

    const [formData, setFormData] = useState({
        name: "",
        email: "",
        phone: "",
        address: ""
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
            const response = await fetch("http://localhost:5173/api/employee", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(formData)
            });

            const data = await response.json();
            console.log("employee created:", data);
            navigate("/");
        } catch (error) {
            console.log("Error creating employee:", error);
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
                            name="name"
                            placeholder="Enter Your Name"
                            value={formData.name}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="email"
                            name="email"
                            placeholder="Enter Your Email"
                            value={formData.email}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="text"
                            name="phone"
                            placeholder="Enter phone number"
                            value={formData.phone}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="text"
                            name="address"
                            placeholder="Enter address"
                            value={formData.address}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Button variant="primary" type="submit">Submit</Button>
                </Form>
            </div>
        </>
    );
}

export default NoMatch;