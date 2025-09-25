import React, { useState } from 'react';
import './PostOrder.css';

interface QualityOption {
    value: string;
    label: string;
}

const PostUserDropdown: React.FC = () => {
    const [selectedValue, setSelectedValue] = useState<string>('');

    const actionOptions: QualityOption[] = [
        { value: 'buy', label: 'Buy'},
        { value: 'sell', label: 'Sell'},
        { value: 'short', label: 'Short'},
        { value: 'btc', label: 'Buy-To-Cover'},
    ];

    const orderOptions: QualityOption[] = [
        { value: 'market', label: 'Market'},
        { value: 'limit', label: 'Limit'},
        { value: 'stop-loss', label: 'Stop-Loss'},
        { value: 'stop-limit', label: 'Stop-Limit'}/*,
        { value: 'trailing-stop-loss', label: 'Trailing-Stop-Loss'},
        { value: 'trailing-stop-limit', label: 'Trailing-Stop-Limit'}*/
    ];

    return (
        <div>

        </div>
    );
};


const PostUserw452534 = () => {

    const [formData, setFormData] = useState({

    })
}