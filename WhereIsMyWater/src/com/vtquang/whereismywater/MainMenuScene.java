package com.vtquang.whereismywater;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.debug.Debug;

public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener{  
 
	private Sprite background;
	private TextureRegion backgroundRegion; 
	
	private Sprite shower; 
	private TextureRegion showerRegion; 
	
	private Sprite logo; 
	private TextureRegion logoRegion; 
	
	private Sprite radio; 
	private TextureRegion radioRegion; 
	
	public TextureRegion play_region;
	public TextureRegion options_region;
	
	private BuildableBitmapTextureAtlas menuTexture;
	private MenuScene childScene;
	private Music music;
	
	public MainMenuScene(SceneManager sceneManager) {
		super(sceneManager); 
	}

	@Override
	public void createScene() {
		loadGraphic();
		CreateBackground();
		CreateLogo();
		CreateMenuChildScene();
		CreateMusic();
	} 
	
	private void CreateBackground() { 
		Sprite background = new Sprite(0,0, GameActivity.CAMERA_WIDTH, 480, backgroundRegion, _Vbom);
		this.setBackground(new SpriteBackground(background)); 
	}
	
	private void CreateLogo() { 
		float widthLogo = 250;
		float heightLogo = 100;
		float x = (GameActivity.CAMERA_WIDTH - widthLogo) / 2;
		float y = 3;
		logo = new Sprite(x, y, widthLogo, heightLogo, logoRegion, _Vbom);
		shower = new Sprite(40, 142, 45, 42, showerRegion, _Vbom);
		radio = new Radio(50, 200, 58, 80, radioRegion, _Vbom);
		this.attachChild(logo);
		this.attachChild(shower);
		this.attachChild(radio);
	}
	 
	private final int MENU_PLAY = 0;
	private final int MENU_OPTIONS = 1;

	private void CreateMenuChildScene()
	{
	    childScene = new MenuScene(this._Camera);
	    childScene.setPosition(0, 0);
	    
	    final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, play_region, _Vbom), 1.1f, 1);
	    final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, options_region, _Vbom), 1.1f, 1);
	    
	    playMenuItem.setSize(128, 55);
	    optionsMenuItem.setSize(128, 55);
	    
	    childScene.addMenuItem(playMenuItem);
	    childScene.addMenuItem(optionsMenuItem);
	    
	    childScene.buildAnimations();
	    childScene.setBackgroundEnabled(false);
	    
	    playMenuItem.setPosition(playMenuItem.getX(), playMenuItem.getY());
	    optionsMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY() - 110);
	    
	    childScene.setOnMenuItemClickListener(this);
	    
	    setChildScene(childScene);
	}
	
	private void CreateMusic(){
		try
		{
		    music = MusicFactory.createMusicFromAsset(((BaseGameActivity) _Activity).getMusicManager(), _Activity, "JAW_Theme_Song.mp3");
		}
		catch (IOException e)
		{
		    e.printStackTrace();
		}
		music.setLooping(true);
		music.play();
	}
	
	private void loadGraphic(){ 
		this.menuTexture = new BuildableBitmapTextureAtlas(((BaseGameActivity) _Activity).getTextureManager(), 1024, 1024, TextureOptions.DEFAULT); 
		loadBackground();
		loadLogo();
		loadButton();
		
		try 
		{
		    this.menuTexture.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
		    this.menuTexture.load();
		} 
		catch (final TextureAtlasBuilderException e)
		{
		        Debug.e(e);
		} 
	}
	
	private void loadButton() {
		this.play_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuTexture, _Activity, "ui_progress_bar_fg.png");		
		this.options_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuTexture, _Activity, "ui_progress_bar_fg.png");		
 	}

	private void loadBackground(){ 
		this.backgroundRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuTexture, _Activity, "main_menu.jpg");		
	}
	
	private void loadLogo(){ 
		this.showerRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuTexture, _Activity, "UI_Shower_Head.png");
		this.logoRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuTexture, _Activity, "wmw_logo.png");
		this.radioRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuTexture, _Activity, "UI_Radio.png");
	}
	
	@Override
	public void onBackKeyPressed() {
		 System.exit(0);
	}

	@Override
	public void disposeScene() {
		background.detachSelf();
		background.dispose();
		
		logo.detachSelf();
		logo.dispose();
		
		this.detachSelf();
		this.dispose();
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_MENU;
	}
	

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		// TODO Auto-generated method stub
		return false;
	} 
}
