# 🤖 RAG Application with Java & LangChain4j

A simple Retrieval-Augmented Generation (RAG) application built with Java and LangChain4j, perfect for learning how RAG systems work.

## 🎯 What is RAG?

RAG (Retrieval-Augmented Generation) is a technique that combines the power of Large Language Models (LLMs) with external knowledge retrieval. Instead of relying solely on the LLM's training data, RAG:

1. **Retrieves** relevant information from a knowledge base
2. **Augments** the user's question with this context  
3. **Generates** a more accurate and contextual response

## 🚀 Features

- Document loading and text chunking
- Vector embeddings generation using AllMiniLmL6V2
- In-memory vector store for similarity search
- Interactive Q&A interface
- Demo mode with sample questions
- Perfect for educational purposes

## 🛠️ Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

## Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/somya2596/agents-in-action.git
   cd agents-in-action/Day-2/QA-Assistant
   ```

2. **Compile and run**
   ```bash
   mvn clean compile exec:java
   ```

3. **Ask specific questions**
   ```bash
   mvn exec:java -Dexec.args="What is RAG?"
   mvn exec:java -Dexec.args="How do agents work with LLMs?"
   ```

## 📁 Project Structure


Day-2/QA-Assistant/
├── pom.xml # Maven configuration
├── src/
│ └── main/
│ ├── java/org/example/
│ │ ├── Main.java # Main application entry point
│ │ └── RagBootstrap.java # Core RAG implementation
│ └── resources/
│ └── seed.txt # Sample knowledge base
└── target/ # Compiled classes


## 🔧 How It Works

1. **Document Processing**: Loads and splits documents into manageable chunks
2. **Embedding Generation**: Creates vector embeddings for each text chunk
3. **Vector Storage**: Stores embeddings in an in-memory vector store
4. **Query Processing**: 
   - Converts user questions to embeddings
   - Finds most similar document chunks
   - Combines context with question
   - Generates contextual responses

## Key Concepts Demonstrated

- **Document Chunking**: Breaking large documents into smaller, searchable pieces
- **Vector Embeddings**: Converting text to numerical representations for similarity search
- **Semantic Search**: Finding relevant information based on meaning, not just keywords
- **Context Augmentation**: Enhancing LLM responses with retrieved knowledge

## 📚 Sample Knowledge Base

The application comes with a sample knowledge base (`seed.txt`) containing:

- Information about RAG and its benefits
- Details about agents and LLMs
- Vector store options (FAISS, pgvector, Chroma)
- LangChain4j capabilities

## 🎯 Perfect for Learning

This project is ideal for:
- Understanding RAG fundamentals
- Learning about vector databases
- Exploring LangChain4j capabilities
- Building your first AI application

##  Medium Article

This code accompanies the Medium article: **"From LangChain to RAG: Building Your First AI Application"**

## 🚀 Quick Start

```bash
# Navigate to the project
cd agents-in-action/Day-2/QA-Assistant

# Run the demo
mvn clean compile exec:java

# Ask a specific question
mvn exec:java -Dexec.args="What is RAG?"
```

## 🔍 Example Output
�� Building Your First RAG Application with Java & LangChain4j
======================================================================
�� Initializing RAG system...
✅ Loaded 1 document segments into vector store
🤖 RAG Assistant Demo
==================================================
==================================================
Question: What is RAG?
Based on the retrieved context:
Context:
Retrieval-Augmented Generation (RAG) reduces hallucinations by fetching relevant facts from a vector database before generation.
Your question: What is RAG?
Answer: RAG (Retrieval-Augmented Generation) reduces hallucinations by fetching relevant facts from a vector database before generation. It combines the power of LLMs with external knowledge retrieval.




## 🤝 Contributing

Feel free to submit issues and enhancement requests!

## 🔗 Related Resources

- [LangChain4j Documentation](https://github.com/langchain4j/langchain4j)
- [RAG Paper](https://arxiv.org/abs/2005.11401)
- [Vector Databases Explained](https://www.pinecone.io/learn/vector-database/)

---

**Built with ❤️ for the AI learning community**
