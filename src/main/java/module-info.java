module com.jcv.fx_movietable {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.jcv.fx_movietable to javafx.fxml;
    exports com.jcv.fx_movietable;
}
