package ru.focusstart;

import ru.focusstart.model.ChatModel;

public class ClientMain {
    public static void main(String[] args) {
        ChatModel chatClient = ChatModel.getInstance();
        chatClient.login();
        chatClient.waitExit();
      /*  if (chatClient.enterToChat()) {
            chatClient.listenToUser();
            //chatClient.listenToServer();
        }*/
/*
        while (!chatClient.isConnect()) {
            while (chatClient.getLogin() == null) {
              //  System.out.println("Пока не нажал кнопку");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
            // System.out.println("Прошли цикл!!!");
            try {
                //chatClient.addObserver(new Facade2());
                chatClient.connectToServer();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(new JFrame(),
                        e.getMessage(),
                        "Ошибка подключения",
                        JOptionPane.ERROR_MESSAGE);
            }finally {
                chatClient.setLogin(null);
            }
        }*/
       // System.out.println("Вышли из цикла");

        //chatClient.deleteObserver(facade1);

    }
}