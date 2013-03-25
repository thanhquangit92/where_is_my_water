package com.vtquang.whereismywater;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

import com.vtquang.whereismywater.BaseScene.SceneType;

public class SceneManager {

	public Engine engine;
	public Camera camera;
    public BaseGameActivity activity; 
    public VertexBufferObjectManager vbom; 
    
    private BaseScene splashScene;
	private BaseScene menuScene;
	private BaseScene loadScene;
	private BaseScene gameScene;
	
	private BaseScene currentScene; 
	
	public SceneManager(Engine engine, BaseGameActivity activity, Camera camera, VertexBufferObjectManager vbom) {
		this.engine = engine;
		this.activity = activity;
		this.camera = camera;
		this.vbom = vbom;
	}
	
	public void setScene(BaseScene scene)
    { 
        engine.setScene(scene);
        currentScene = scene; 
    } 
	
	public void setScene(SceneType type) {
		switch (type) {
		case SCENE_SPLASH:
			setScene(splashScene);
			break;
		case SCENE_GAME:
			setScene(gameScene);
			break;
		case SCENE_LOADING:
			setScene(loadScene);
			break;
		case SCENE_MENU:
			setScene(menuScene);
			break;
		default:
			break;
		}
	}
	
	public SceneType getCurrentSceneType()
    {
        return currentScene.getSceneType();
    }
    
    public BaseScene getCurrentScene()
    {
        return currentScene;
    }
}
