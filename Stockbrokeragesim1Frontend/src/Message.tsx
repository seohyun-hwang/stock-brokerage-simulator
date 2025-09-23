function Message() {
    const name = 'MyName';
    if (name) // if the variable "name" has a value
        return <h1>Hello {name}!</h1>;
    return <h1>Hello World!</h1>; // JSX (JavaScript XML) format
}

export default Message; // export as default object so it can be used outside this file