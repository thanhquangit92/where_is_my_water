package com.vtquang.whereismywater;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.Font;
import org.andengine.ui.activity.BaseGameActivity;

import android.content.Context;
import android.view.KeyEvent;


public class GameActivity extends BaseGameActivity {

	public static int CAMERA_WIDTH = 320;
	public static int CAMERA_HEIGHT = 480;  
	
	private static BaseGameActivity INSTANCE;
	
	public Font mFont;
	public Camera mCamera;
	public Scene mCurrentScene;
	public SceneManager sceneManager; 
	
	@Override
	protected synchronized void onCreateGame() { 
		super.onCreateGame();
		INSTANCE = this;
	}

	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) 
	{
	    return new LimitedFPSEngine(pEngineOptions, 60);
	}
	
	@Override
	public EngineOptions onCreateEngineOptions() { 
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions engOps = new EngineOptions(true, ScreenOrientation.PORTRAIT_SENSOR, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
		engOps.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
		engOps.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		return engOps;
	} 
	 
	@Override
	public final void onCreateResources(final OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
		sceneManager = new SceneManager(mEngine, this, mCamera, getVertexBufferObjectManager());
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public final void onCreateScene(final OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
		//Scene scene = new Scene();
		//scene.setBackground(new Background(Color.BLUE));
		//Sprite sprite = new Sprite(10, 300, this.mPlayerTextureRegion, this.getVertexBufferObjectManager());
		//scene.attachChild(sprite);
		MainMenuScene menu = new MainMenuScene(sceneManager);
		sceneManager.setScene(menu);
		 
		pOnCreateSceneCallback.onCreateSceneFinished(sceneManager.getCurrentScene());
	}

	@Override
	public final void onPopulateScene(final Scene pScene, final OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	} 
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (this.isGameLoaded()) {
			//sceneManager.dispose();
			System.exit(0);
		}
	}
	
	public static Context getContext(){
		return INSTANCE;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{  
	    if (keyCode == KeyEvent.KEYCODE_BACK)
	    {
	       sceneManager.getCurrentScene().onBackKeyPressed();
	    }
	    return false; 
	}
}
