-----------------------------------------🧑‍💻 Day 2 — Hands-On RAG in Java with LangChain4j-----------------------------------------
                                            Part of the Agents in Action series

This project demonstrates how to build a Retrieval-Augmented Generation (RAG) assistant in Java using LangChain4j

👉 Unlike most tutorials, this version runs fully local using the AllMiniLmL6V2 embedding model. No OpenAI API key is required.

✨ What You’ll Learn

How to split and embed documents into vectors
How to store and search embeddings in memory
How to retrieve top-k relevant context for a query
How to generate a simple grounded answer from context
How to upgrade later to OpenAI or vector DBs

🧱 Tech Stack

Java 21
Maven
LangChain4j
langchain4j (core)
langchain4j-embeddings-all-minilm-l6-v2 (local embeddings)
langchain4j-open-ai (optional, for LLMs)
SLF4J Simple (logging)

📂 Project Structure
QA-Assistant/
├─ pom.xml
├─ src/
│  └─ main/
│     ├─ java/
│     │  └─ org/example/
│     │     ├─ Main.java          # Entry point / CLI
│     │     └─ RagBootstrap.java  # RAG pipeline setup
│     └─ resources/
│        └─ seed.txt              # Knowledge base
└─ README.md


🚀 Getting Started
 1. Clone the repo
   git clone https://github.com/<your-username>/agents-in-action-day2.git
   cd agents-in-action-day2

 2.  Verify prerequisites
      java -version
      mvn -v

 3. Build :  mvn clean compile
 4. Run in demo mode: mvn exec:java
 5. Ask your own Question
    mvn exec: java -Dexec.args= "What is RAG"

 🧠 How It Works

Load KB → seed.txt from resources
Split → text split into 300-char segments
Embed → each segment embedded using AllMiniLmL6V2EmbeddingModel
Store → embeddings stored in InMemoryEmbeddingStore
Retrieve → top-2 relevant segments fetched for a query
Answer → rule-based answer generated from retrieved context

📊 Example Output
Building your First RAG Application with Java & LangChain4j
======================================================================
Loaded 4 document segments into vector store

Question: What is RAG?
Based on the retrieved context:

Context:
Retrieval-Augmented Generation (RAG) reduces hallucinations...

Answer: RAG reduces hallucinations by combining LLMs with retrieval from a vector store.


📑 Knowledge Base
Default seed.txt includes:

Agents extend Large Language Models (LLMs) with tools, memory, and goals...
RAG reduces hallucinations by fetching relevant facts...
LangChain4j connects LLMs with embeddings, vector stores, tools...
FAISS/pgvector/Chroma can be used as vector stores...

👉 Replace this file with your own knowledge base.

🔌 Upgrade Path

Currently, the assistant uses a rule-based answer generator.
To use an LLM for richer answers:
Add your OpenAI API key:
export OPENAI_API_KEY=sk-...
Swap generateResponse() with an OpenAiChatModel call.
Replace in-memory store with Chroma, FAISS, or pgvector for persistence.

🔗 Series Context

This repo is part of the Agents in Action series:

Day 1 → How AI and LLMs Actually Work (And Why Agents Are the Next Big Leap)
Day 2 → Hands-On RAG in Java with LangChain4j (this repo)
Day 3 (coming soon) → Vector Databases for Long-Term Memory

Read the full series on Medium : https://medium.com/technology-hits/agents-in-action-a-new-learning-series-on-building-context-aware-ai-6d5c008f19ca



