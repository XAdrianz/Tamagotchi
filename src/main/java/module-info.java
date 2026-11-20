module org.uabc2109072.tamagotchi {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.uabc2109072.tamagotchi to javafx.fxml;
    exports org.uabc2109072.tamagotchi;
}