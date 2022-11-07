package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
//BU Class da yani expecteddata da olmayan ama actual data da olanlari gormezden gelmek icin kullanilir ve mutlaka true yapılmalı)
public class JsonplaceholderPojo {

   private Integer userId;
   private String title;
   private Boolean completed;

   public JsonplaceholderPojo(Integer userId, String title, Boolean completed) {
      this.userId = userId;
      this.title = title;
      this.completed = completed;
   }

   public JsonplaceholderPojo() {
   }

   public Integer getUserId() {
      return userId;
   }

   public void setUserId(Integer userId) {
      this.userId = userId;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public Boolean getCompleted() {
      return completed;
   }

   public void setCompleted(Boolean completed) {
      this.completed = completed;
   }

   @Override
   public String toString() {
      return "JsonplaceholderPojo{" +
              "userId=" + userId +
              ", title='" + title + '\'' +
              ", completed=" + completed +
              '}';
   }
}
