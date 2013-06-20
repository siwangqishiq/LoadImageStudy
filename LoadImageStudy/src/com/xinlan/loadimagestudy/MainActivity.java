package com.xinlan.loadimagestudy;

import com.xinlan.components.ImageCache;
import com.xinlan.components.ImageFetcher;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {
	public static final String IMAGE_CACHE_DIR="xinlanimages";
	private ImageView img1, img2, img3;
	
	private String url1 = "http://pic19.nipic.com/20120310/9396498_114312191111_2.jpg";
	private String url2 = "http://i9.hexunimg.cn/2011-12-04/135957559.jpg";
	private String url3 = "http://a2.att.hudong.com/82/59/01300000335934123596594255260.jpg";
	
	private ImageFetcher mImageFetcher;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img1 = (ImageView) findViewById(R.id.img1);
		img2 = (ImageView) findViewById(R.id.img2);
		img3 = (ImageView) findViewById(R.id.img3);
		
		mImageFetcher = genFetcher(this);
		mImageFetcher.loadImage(url1, img1);
		mImageFetcher.loadImage(url2, img2);
		mImageFetcher.loadImage(url3, img3);
	}

	private  ImageFetcher genFetcher(FragmentActivity context) {
		final DisplayMetrics displayMetrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		int height = displayMetrics.heightPixels;
		int width = displayMetrics.widthPixels;
		final int longest = (height > width ? height : width) / 2;
		ImageCache.ImageCacheParams cacheParams = new ImageCache.ImageCacheParams(
				context, IMAGE_CACHE_DIR);
		cacheParams.setMemCacheSizePercent(context, 0.25f); // 设置一级缓存大小
		ImageFetcher mImageFetcher = new ImageFetcher(context, longest);
		mImageFetcher.addImageCache(context.getSupportFragmentManager(),
				cacheParams);
		mImageFetcher.setLoadingImage(R.drawable.ic_launcher);
		mImageFetcher.setImageFadeIn(false);
		return mImageFetcher;
	}
}// end class
