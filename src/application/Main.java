package application;
	
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public void start(Stage theStage) 
    {
        theStage.setTitle( "Пример работы с клавиатурой" );
 
        // Готовим корневой элемент и сцену для окна
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
 
        // Указываем размер канвы и добавляем ее в корневой элемент
        Canvas canvas = new Canvas( 512 - 64, 256 );
        root.getChildren().add( canvas );
 
        // Создаем список с кодами нажатых клавиш
        ArrayList<String> input = new ArrayList<String>();
 
        // Обработка события нажатия на клавишу
        theScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                	// код клавиши
                    String code = e.getCode().toString(); 
                    if ( !input.contains(code) )
                        input.add( code );
                }
            });
 
        // обработка "отпускания" клавиши
        theScene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    input.remove( code );
                }
            });
 
        GraphicsContext gc = canvas.getGraphicsContext2D();
 
        // Создаем объекты изображений
        
        Image left = new Image( "left.png" );
        Image leftG = new Image( "leftG.png" );
 
        Image right = new Image( "right.png" );
        Image rightG = new Image( "rightG.png" );
 
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Каждый раз очищаем канву
                gc.clearRect(0, 0, 512,512);
 
                // Меняем изображение, в зависимости от кода клавиши
                if (input.contains("LEFT"))
                    gc.drawImage( leftG, 64, 64 );
                else
                    gc.drawImage( left, 64, 64 );
 
                if (input.contains("RIGHT"))
                    gc.drawImage( rightG, 256, 64 );
                else
                    gc.drawImage( right, 256, 64 );
            }
        }.start();
 
        theStage.show();
    }
	public static void main(String[] args)
    {
        launch(args);
    }

}
