package com.model;

import java.util.ArrayList;

public class ContainerInfo {
   String containerId;
   String image;
   String command;
   String create;
   String status;
   String port;
   String names;

   public ContainerInfo(String lineInfo) {
      String[] arr = lineInfo.trim().split("  ");
      setNames(arr[arr.length - 1]);
      arr[arr.length - 1] = "";
      ArrayList<String> arrNotEmpty = new ArrayList<>();

      for (int i = 0; i < arr.length; i++) {
         if(!arr[i].trim().equals("")) {
            arrNotEmpty.add(arr[i]);
         }
      }

      while (arrNotEmpty.size() < 6) {
         arrNotEmpty.add("");
      }

      setContainerId(arrNotEmpty.get(0).replace("\n", " "));
      setImage(arrNotEmpty.get(1).replace("\n", " "));
      setCommand(arrNotEmpty.get(2).replace("\n", " "));
      setCreate(arrNotEmpty.get(3).replace("\n", " "));
      setStatus(arrNotEmpty.get(4).replace("\n", " "));
      setPort(arrNotEmpty.get(5).replace("\n", " "));
   }

   public String toJson() {
      return "{" +
              "\"containerId\":\"" + containerId + "\"," +
              "\"image\":\"" + image + "\"," +
              //"\"command\":\"" + command + "\"," +
              "\"create\":\"" + create + "\"," +
              "\"status\":\"" + status + "\"," +
              "\"port\":\"" + port + "\"," +
              "\"names\":\"" + names + "\"" +
              "}".replace("'", "''")
                 .replace("/", "//");
   }

   public String getContainerId() {
      return containerId;
   }

   public void setContainerId(String containerId) {
      this.containerId = containerId;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getCommand() {
      return command;
   }

   public void setCommand(String command) {
      this.command = command;
   }

   public String getCreate() {
      return create;
   }

   public void setCreate(String create) {
      this.create = create;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getPort() {
      return port;
   }

   public void setPort(String port) {
      this.port = port;
   }

   public String getNames() {
      return names;
   }

   public void setNames(String names) {
      this.names = names;
   }
}
