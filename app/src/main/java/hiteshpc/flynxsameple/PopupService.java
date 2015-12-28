package hiteshpc.flynxsameple;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.IBinder;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
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
        popupHead.setImageResource(R.drawable.floating5);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 10;
        params.y = 100;

        windowManager.addView(popupHead, params);
//        popupHead.setClickable(true);

        popupHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                System.out.println("the helll");
                openPopup(popupHead);
            }
        });
           /*try {
               popupHead.setOnTouchListener(new View.OnTouchListener() {

                   private int initialX;
                   private int initialY;
                   private float initialTouchX;
                   private float initialTouchY;

                   @Override
                   public boolean onTouch(View v, MotionEvent event) {
                       switch (event.getAction()) {
                           case MotionEvent.ACTION_DOWN:
                               initialX = params.x;
                               initialY = params.y;
                               initialTouchX = event.getRawX();
                               initialTouchY = event.getRawY();
                               return true;
                           case MotionEvent.ACTION_UP:
                               return true;
                           case MotionEvent.ACTION_MOVE:
                               params.x = initialX + (int) (event.getRawX() - initialTouchX);
                               params.y = initialY + (int) (event.getRawY() - initialTouchY);
                               windowManager.updateViewLayout(popupHead, params);
                               return true;
                       }
                       return false;
                   }
               });
           } catch (Exception e){

           }*/

        }

        private void openPopup(View anchor) {
            try {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    /*Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
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
