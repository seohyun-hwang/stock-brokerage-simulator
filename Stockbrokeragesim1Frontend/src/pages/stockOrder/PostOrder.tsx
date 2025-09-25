import { useState } from "react";
import "./PostOrder.css";
import Form from "react-bootstrap/Form"
import Button from "react-bootstrap/Button";
import {useNavigate} from "react-router-dom";

const PostOrder = () => {

    const [formData, setFormData] = useState({
        stockTicker: "",
        actionType: "",
        orderType: "",
        orderCount_ofThisInstance: "",
        durationInDays: "",
        limitPrice: "",
        stopPrice: ""/*,
        trailTriggerDelta: "",
        trailTriggerPercentage: ""*/
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
                            name="stockTicker"
                            placeholder="Stock Ticker"
                            value={formData.stockTicker}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="email"
                            name="actionType"
                            placeholder="Action Type"
                            value={formData.actionType}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="text"
                            name="orderType"
                            placeholder="Order Type"
                            value={formData.orderType}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="text"
                            name="orderCount_ofThisInstance"
                            placeholder="Order Quantity"
                            value={formData.orderCount_ofThisInstance}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="text"
                            name="durationInDays"
                            placeholder="Order Duration"
                            value={formData.durationInDays}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="text"
                            name="limitPrice"
                            placeholder="Limit Price"
                            value={formData.limitPrice}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="text"
                            name="stopPrice"
                            placeholder="Stop Price"
                            value={formData.stopPrice}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Button variant="primary" type="submit">Submit Order</Button>
                </Form>
            </div>
        </>
    );
}

export default PostOrder;