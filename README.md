


# 🎉 ElectroDeals : A E-commerce platform 🚀  

An e-commerce platform for managing and purchasing electronics products, built with **Spring Boot**, **Hibernate**, and **MySQL**. 🛒✨  

---

## 🌟 Features  

- **👤 User Management**:  
  - 📝 Register, 🔑 login, and 🛠️ manage user profiles.  
  - 📊 Pagination and 🔃 sorting for user data.  

- **📦 Product Management**:  
  - ➕ Add, ✏️ update, and 👁️ view product details.  
  - 🔍 Search for products based on 📂 categories or 🔑 keywords.  

- **🛒 Shopping Cart**:  
  - 🛍️ Add items to the cart.  
  - 🧹 Manage and ❌ remove items from the cart.  

- **📜 Order Management**:  
  - 🆕 Create and 🗂️ track orders.  

- **🏗️ Robust Architecture**:  
  - Layered design with 🧑‍💻 Controllers, 💼 Services, 📂 Repositories, and ✨ DTOs.  
  - 🚨 Exception handling for a smooth user experience.  

---

## 🛠️ Tech Stack  

- **Backend**: ⚙️ Spring Boot, Hibernate  
- **Database**: 🛢️ MySQL  
- **Tools**: 🧪 Postman for API testing  
- **Version Control**: 🗂️ GitHub  

---

## 📦 Installation and Setup  

1. **🛑 Clone the repository**:  
   ```bash  
   git clone https://github.com/your-username/electronics-deals.git  
   cd electronics-deals  
   ```  

2. **⚙️ Configure the Database**:  
   ```properties  
   spring.datasource.url=jdbc:mysql://localhost:3306/electronicsdb  
   spring.datasource.username=your_username  
   spring.datasource.password=your_password  
   ```  

3. **▶️ Run the application**:  
   ```bash  
   mvn spring-boot:run  
   ```  

4. **🧪 Test APIs**:  
   Use Postman to test endpoints (e.g., `/api/products`, `/api/cart`).  

---

## 📋 API Endpoints  

### **👤 User Management**  
| 🛠️ HTTP Method | 🔗 Endpoint          | 📝 Description                 |  
|----------------|--------------------|-----------------------------|  
| `GET`         | `/api/users`       | 🧑‍🤝‍🧑 Fetch all users        |  
| `POST`        | `/api/users`       | ➕ Add a new user             |  
| `PUT`         | `/api/users/{id}`  | ✏️ Update user details        |  
| `DELETE`      | `/api/users/{id}`  | ❌ Delete a user by ID        |  

### **📦 Product Management**  
| 🛠️ HTTP Method | 🔗 Endpoint               | 📝 Description                      |  
|----------------|------------------------|----------------------------------|  
| `GET`         | `/api/products`        | 👀 Fetch all products              |  
| `GET`         | `/api/products/{id}`   | 🔍 Fetch product details by ID     |  
| `POST`        | `/api/products`        | ➕ Add a new product               |  
| `PUT`         | `/api/products/{id}`   | ✏️ Update product details          |  
| `DELETE`      | `/api/products/{id}`   | ❌ Delete a product by ID          |  

### **🛒 Cart Management**  
| 🛠️ HTTP Method | 🔗 Endpoint               | 📝 Description                      |  
|----------------|------------------------|----------------------------------|  
| `POST`        | `/api/cart/add`        | 🛒 Add an item to the cart         |  
| `GET`         | `/api/cart`            | 👀 View all items in the cart      |  
| `DELETE`      | `/api/cart/remove/{id}`| ❌ Remove an item from the cart    |  

### **📜 Order Management**  
| 🛠️ HTTP Method | 🔗 Endpoint               | 📝 Description                      |  
|----------------|------------------------|----------------------------------|  
| `POST`        | `/api/orders/create`   | ➕ Create a new order              |  
| `GET`         | `/api/orders/{id}`     | 🔍 Fetch order details by ID       |  
| `GET`         | `/api/orders`          | 👥 Fetch all orders for a user     |  

---

## 🤝 Contributing  

1. 🍴 Fork the repository.  
2. 🛠️ Create a feature branch:  
   ```bash  
   git checkout -b feature-name  
   ```  
3. ✏️ Commit your changes:  
   ```bash  
   git commit -m "Add feature"  
   ```  
4. 📤 Push to the branch:  
   ```bash  
   git push origin feature-name  
   ```  
5. 📥 Submit a pull request.  

---

🎉 Thank you for checking out the **Electronics Deals E-commerce Website**! Feel free to contribute, explore, and make it better! 😄💻
