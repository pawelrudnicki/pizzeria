import React from 'react';
import {Table, Button ,Modal, ModalBody, ModalHeader, ModalFooter, Input, Label, FormGroup,
        Card, CardText, CardBody, CardTitle}
    from "reactstrap";
import axios from "axios";

class App extends React.Component {

    state = {
        products: [],
        newProductData: {
            name: '',
            price: ''
        },
        newProductModal: false,
        orders: [],
        orderedProducts: []
    };

    componentWillMount() {
        axios.get('http://localhost:8080/products').then((response) => {
            this.setState({
                products: response.data
            })
        });

        axios.get('http://localhost:8080/orders').then((response) => {
           this.setState({
               orders: response.data
           })
        });
    }

    toggleNewProductModal() {
        this.setState({
            newProductModal: !this.state.newProductModal
        })
    }


    addProduct() {
        axios.post('http://localhost:8080/products', this.state.newProductData).then((response) => {
            let { products } = this.state;

            products.push(response.data);

            this.setState({
                products, newProductModal: false, newProductData: {
                    name: '',
                    price: ''
                }
            });

            this._refreshProducts();
        });
    }

    deleteProduct(id) {
        axios.delete('http://localhost:8080/products/' + id).then((response) => {
            this._refreshProducts();
        });
    }

    deleteOrder(id) {
        axios.delete('http://localhost:8080/orders/' + id).then((response) => {
            this._refreshOrders();
        })
    }

    _refreshProducts() {
        axios.get('http://localhost:8080/products').then((response) => {
           this.setState({
               products: response.data
           })
        });
    }

    _refreshOrders() {
        axios.get('http://localhost:8080/orders').then((response) => {
            this.setState({
                orders: response.data
            })
        })
    }

    async getInputsValue() {
        let checks = document.getElementsByClassName("productsInputs");
        for (let i = 0; i < this.state.products.length; i++) {
            if (checks[i].checked === true) {
                await axios.get('http://localhost:8080/products/' + checks[i].value).then((response) => {
                    this.state.orderedProducts.push(response.data)
                });
                checks[i].checked = false;
            }
        }
        console.log("REEEEEEE", this.state.orderedProducts);

        await axios.post('http://localhost:8080/orders', this.state.orderedProducts);

        this.setState({
            orderedProducts: []
        });

        this._refreshOrders();
    }

    render() {
        let products = this.state.products.map((product) => {
            return (
                <tr key={product.id}>
                    <td>{product.id}</td>
                    <td>{product.name}</td>
                    <td>{product.price} $</td>
                    <td>
                        <input type="checkbox" className="productsInputs" style={{marginRight: '15px'}} value={product.id}/>
                        <Button color="danger" size="sm" onClick={this.deleteProduct.bind(this, product.id)}>Remove product</Button>
                    </td>
                </tr>
            );
        });

        let orders = this.state.orders.map((order) => {
            return (

                <Card style={{marginBottom: '0.5em'}}>
                    <CardBody>
                        <CardTitle>Order: {order.id}</CardTitle>
                        <CardText>Price: {order.orderPrice} $</CardText>
                        <Button color="danger" size="sm" onClick={this.deleteOrder.bind(this, order.id)}>Remove order</Button>
                    </CardBody>
                </Card>
            );
        });
        return (
            <div className="App container">

                <h1>Pizza App</h1>

                <Button className="my-3" color="primary" onClick={this.toggleNewProductModal.bind(this)}>Add Product</Button>
                <Modal isOpen={this.state.newProductModal} toggle={this.toggleNewProductModal.bind(this)}>
                    <ModalHeader toggle={this.toggleNewProductModal.bind(this)}>Add a new product</ModalHeader>
                    <ModalBody>
                        <FormGroup>
                            <Label for="name">Name</Label>
                            <Input id="name" value={this.state.newProductData.name} onChange={(e) => {
                                let { newProductData } = this.state;
                                newProductData.name = e.target.value;
                                this.setState({ newProductData });
                            }}/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="price">Price</Label>
                            <Input type="number" step="any" id="price" value={this.state.newProductData.price} onChange={(e) => {
                                let { newProductData } = this.state;
                                newProductData.price = e.target.value;
                                this.setState({ newProductData });
                            }}/>
                        </FormGroup>
                    </ModalBody>
                    <ModalFooter>
                        <Button color="success" onClick={this.addProduct.bind(this)}>Add!</Button>
                        <Button color="warning" onClick={this.toggleNewProductModal.bind(this)}>Cancel</Button>
                    </ModalFooter>
                </Modal>

                <Table>
                    <thead>
                        <th>#</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Actions</th>
                    </thead>

                    <tbody>
                        {products}
                    </tbody>
                </Table>

                <Button onClick={this.getInputsValue.bind(this)} color="success" size="lg" block>Add order</Button>

                <h1>Orders</h1>

                <Table>
                    {orders}
                </Table>
            </div>

        );
    }
}

export default App;
