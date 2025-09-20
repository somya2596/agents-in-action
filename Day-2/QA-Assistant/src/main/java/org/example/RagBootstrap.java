package org.example;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.store.embedding.EmbeddingMatch;

import java.io.File;
import java.util.List;

public class RagBootstrap {
    public interface Assistant {
        String answer(String question);
    }

    private final Assistant assistant;
    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;

    public RagBootstrap() {
        // Initialize embedding model (using local model for demo)
        this.embeddingModel = new AllMiniLmL6V2EmbeddingModel();

        // Initialize in-memory vector store
        this.embeddingStore = new InMemoryEmbeddingStore<>();

        // Load and process documents
        loadDocuments();

        // Initialize the RAG assistant
        this.assistant = createRAGAssistant();

    }

    private void loadDocuments(){
        try{
            //load the seed document
            Document document = FileSystemDocumentLoader.loadDocument(new File("src/main/resources/seed.txt").toPath(),new TextDocumentParser());

            //split document into smaller chunks for better retrieval
            DocumentSplitter splitter = DocumentSplitters.recursive(300, 0);
            List<TextSegment> segments = splitter.split(document);

            //Generate embeddings and store them

                for(TextSegment segment : segments){
                    Embedding embedding = embeddingModel.embed(segment).content();
                    embeddingStore.add(embedding,segment);
                }

            System.out.println("Loaded " + segments.size() + " document segments into vector store");
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("error loading documents" + e.getMessage());
        }
    }

    private Assistant createRAGAssistant(){

        //for demo purposes, we'll use simple implementation
        //In production, you'd use OpenAIChatModel with your API Key
        return new Assistant() {
            @Override
            public String answer(String question) {
                try{
                    //generate embedding for the question
                    Embedding questionEmbedding = embeddingModel.embed(question).content();

                   // Retrieve relevant segments (returns EmbeddingMatch objects)
                   List<EmbeddingMatch<TextSegment>> relevantMatches = embeddingStore.findRelevant(questionEmbedding, 2);
                   
                     // Build context from retrieved segments
                     StringBuilder context = new StringBuilder();
                     for (EmbeddingMatch<TextSegment> match : relevantMatches) {
                         context.append(match.embedded().text()).append("\n\n");
                     }

                    //simple response generation (in production, use LLM)
                    return generateResponse(question, context.toString());
                }catch (Exception e){
                    return  "Sorry, I encountered some error" + e.getMessage();
                }
            }
        };
    }

    private String generateResponse(String question, String context){
        // This is a simplified response generator
        // In a real RAG system, you'd use an LLM like GPT-4

        StringBuilder response = new StringBuilder();
        response.append("🤖 Based on the retrieved context:\n\n");
        response.append("📚 Context:\n").append(context).append("\n");
        response.append("❓ Your question: ").append(question).append("\n\n");
        response.append("💡 Answer: ");

        // Simple keyword-based response (replace with actual LLM in production)
        if (question.toLowerCase().contains("rag") || question.toLowerCase().contains("retrieval")) {
            response.append("RAG (Retrieval-Augmented Generation) reduces hallucinations by fetching relevant facts from a vector database before generation. It combines the power of LLMs with external knowledge retrieval.");
        } else if (question.toLowerCase().contains("agent") || question.toLowerCase().contains("llm")) {
            response.append("Agents extend Large Language Models (LLMs) with tools, memory, and goals so they can act in the real world. LangChain4j connects LLMs with embeddings, vector stores, tools, and memory.");
        } else if (question.toLowerCase().contains("vector") || question.toLowerCase().contains("store")) {
            response.append("Vector stores like FAISS, pgvector, or Chroma can be used to store and retrieve embeddings. Here we're using an in-memory store for simplicity.");
        } else {
            response.append("I found relevant information in the context above. Please ask a more specific question about RAG, agents, or vector stores for a detailed answer.");
        }

        return response.toString();
    }

    public Assistant getAssistant() {
        return assistant;
    }

    // Demo method to show how RAG works
    public void demonstrateRAG() {
        System.out.println("�� RAG Assistant Demo");
        System.out.println("=" .repeat(50));

        String[] questions = {
                "What is RAG?",
                "How do agents work with LLMs?",
                "What vector stores can I use?",
                "Tell me about LangChain4j"
        };

        for (String question : questions) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("Question: " + question);
            System.out.println("-".repeat(50));
            System.out.println(assistant.answer(question));
        }
    }

}
