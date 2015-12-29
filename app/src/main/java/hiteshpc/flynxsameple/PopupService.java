package hiteshpc.flynxsameple;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

public class PopupService extends Service {
    public PopupService() {
    }

    private WindowManager windowManager;
    private ImageView popupHead;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(getApplicationContext(),"is is also working the service", Toast.LENGTH_SHORT).show();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        popupHead = new ImageView(this);
        popupHead.setImageResource(R.drawable.pophead);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 10;
        params.y = 30;

        windowManager.addView(popupHead, params);
//        popupHead.setClickable(true);

        popupHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                System.out.println("the helll");
                params.gravity = Gravity.TOP | Gravity.RIGHT;
                params.x = 10;
                params.y = 30;
                windowManager.updateViewLayout(popupHead, params);
                openPopup(popupHead);
            }

            /*private void openPopup(View anchor) {
                WebView wv = new WebView(getApplicationContext());
//                wv.setVerticalScrollBarEnabled(false);
//                wv.setHorizontalScrollBarEnabled(false);
                wv.setWebViewClient(new WebViewClient());
                wv.getSettings().setJavaScriptEnabled(true);
//                view.addView(wv);
                wv.loadUrl("http://google.com");
                windowManager.addView(wv,params);
            }*/
        });
        popupHead.setOnTouchListener(new View.OnTouchListener() {

            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = arg1.getRawX();
                        initialTouchY = arg1.getRawY();
                        return false;
                    case MotionEvent.ACTION_UP:
                        return false;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (arg1.getRawX() - initialTouchX);
                        params.y = initialY + (int) (arg1.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(popupHead, params);
                        return false;
                }
                return false;
            }
        });

        }
        private void openPopup(View anchor) {
            try {
                final WindowManager.LayoutParams Webparams = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_PHONE,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT);

                Webparams.gravity = Gravity.TOP | Gravity.CENTER;
                Webparams.x = 10;
                Webparams.y = 200;
                Webparams.width =850;
                Webparams.height = 1500;


                final WebView wv = new WebView(getApplicationContext());
//                wv.setVerticalScrollBarEnabled(false);
//                wv.setHorizontalScrollBarEnabled(false);
                wv.setWebViewClient(new WebViewClient());
                wv.getSettings().setJavaScriptEnabled(true);
                wv.setFocusable(true);
                wv.setFocusableInTouchMode(true);
                wv.requestFocus(wv.FOCUS_DOWN);
//                view.addView(wv);
                wv.loadUrl("http://google.com");
                windowManager.addView(wv, Webparams);
                popupHead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            windowManager.removeView(wv);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                    /*Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                    ListPopupWindow popup = new ListPopupWindow(getApplicationContext());

                    popup.setAnchorView(anchor);
                    Point size = new Point();
                    display.getSize(size);
                    int width = size.x;
                    int height = size.y;
                    System.out.println("this is wid " + width + " and this is hif " + height);
                    popup.setWidth((int) (width / 1.1));
                    popup.setHeight((int) (height/1.3));
                    popup.show();*/

            }catch (Exception e){
                e.printStackTrace();

            }
        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (popupHead != null) windowManager.removeView(popupHead);
    }
}
