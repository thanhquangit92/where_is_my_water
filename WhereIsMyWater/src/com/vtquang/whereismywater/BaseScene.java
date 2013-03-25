package com.vtquang.whereismywater;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.app.Activity;
 
public abstract class BaseScene extends Scene {
	public static enum SceneType
	{
		SCENE_SPLASH,
        SCENE_MENU,
        SCENE_LOADING,
        SCENE_GAME, 
	}
	
	protected SceneManager _SceneManager;
	
	protected Camera _Camera;
	protected Engine _Engine;
	protected Activity  _Activity;   
	protected VertexBufferObjectManager _Vbom; 
	
	public BaseScene(SceneManager sceneManager) {
		this._SceneManager = sceneManager;
		this._Camera = sceneManager.camera;
		this._Activity = sceneManager.activity; 
		this._Vbom = sceneManager.vbom; 
		createScene();
	}

	// Method
	public abstract void createScene(); 
	public abstract void onBackKeyPressed(); 
	public abstract void disposeScene();
	public abstract SceneType getSceneType();
}
