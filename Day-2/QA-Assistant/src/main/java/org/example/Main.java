package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Building your First RAG Application with Java & LangChain4j");
        System.out.println("=".repeat(70));

        try {
            // Initialize RAG system
            System.out.println("🔄 Initializing RAG system...");
            RagBootstrap ragBootstrap = new RagBootstrap();

            //get the assistant
            RagBootstrap.Assistant assistant = ragBootstrap.getAssistant();

            //Interactive demo
            if (args.length > 0) {
                String question = String.join("  ", args);
                System.out.println("\n ? Question:" + question);
                System.out.println("\n ? Answer:");
                System.out.println(assistant.answer(question));


            } else {
                //run demonstration
                ragBootstrap.demonstrateRAG();

            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}