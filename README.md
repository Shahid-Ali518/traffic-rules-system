# 🚦 Traffic Rules Assistant System (Microservices)

An AI-powered, microservices-based platform designed to manage traffic regulations and provide natural language legal assistance. This system demonstrates advanced software architecture patterns, including service discovery, centralized auditing, and Retrieval-Augmented Generation (RAG).

## 🏗️ System Architecture
The system is built using a **Microservices Architecture** to ensure high availability and independent scalability of core business components.



### Core Modules:
* **Eureka Server:** Centralized Service Registry for dynamic service discovery.
* **User Service:** Manages authentication and Role-Based Access Control (RBAC) via JWT.
* **Traffic Service:** Handles core logic for traffic rules, fine management, and violation records.
* **QA (AI) Service:** Integrated with **Spring AI** and **Ollama** using a **RAG** architecture to provide context-aware legal assistance.
* **Audit Service:** Asynchronously captures system logs and transaction history for security compliance.
* **Common Module:** A shared library providing unified DTOs and exception handling across the cluster.

## 🧠 AI Integration (RAG Workflow)
Unlike standard chatbots, the QA module uses **Retrieval-Augmented Generation**. When a user asks a question, the system:
1.  Retrieves relevant traffic laws from the **PostgreSQL** database.
2.  Feeds the law text as context to the **Ollama LLM**.
3.  Generates a legally accurate response, eliminating AI hallucinations.



[Image of RAG (Retrieval-Augmented Generation) flow diagram]


## 🛠️ Technical Stack
* **Backend:** Java 17+, Spring Boot, Spring Cloud Netflix Eureka
* **AI/LLM:** Spring AI, Ollama, RAG (Retrieval-Augmented Generation)
* **Security:** Spring Security, JWT (JSON Web Tokens)
* **Database:** PostgreSQL (Relational Law Data)
* **Tools:** Git, Docker, Postman, Maven

## ⚙️ Installation & Setup

1.  **Start Discovery Server:**
    ```bash
    cd eureka-server && mvn spring-boot:run
    ```

2.  **Start Application Modules:**
    Run the following in separate terminals (Common must be built first):
    ```bash
    mvn clean install -pl common
    mvn spring-boot:run -pl user
    mvn spring-boot:run -pl traffic
    mvn spring-boot:run -pl qa
    ```

3.  **Local LLM Requirements:**
    Ensure **Ollama** is installed and the model is pulled:
    ```bash
    ollama run llama3
    ```

## 🌟 Key Engineering Highlights
* **Service Discovery:** Decoupled service communication via Eureka, avoiding hardcoded IP addresses.
* **Decoupled Auditing:** The Audit module ensures all high-stakes actions (like issuing fines) are logged independently.
* **Local AI Inference:** High-privacy AI processing using local models through Spring AI integration.

---
Developed by [Shahid Ali](https://github.com/Shahid-Ali518)
