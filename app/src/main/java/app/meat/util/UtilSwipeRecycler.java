package app.meat.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

public class UtilSwipeRecycler {


    public ItemTouchHelper getItemTouchHelper_swipeLeft(Context context,
                                                        @ColorRes int colorRes,
                                                        @DrawableRes int iconRes,
                                                        OnLeftSwipeListener onLeftSwipeListener) {
        return new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    onLeftSwipeListener.onLeftSwipe(viewHolder.getAdapterPosition());
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView,
                                    RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY, int actionState,
                                    boolean isCurrentlyActive) {

                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemView = viewHolder.itemView;
                    if (dX < 0) {
                        Paint p = new Paint();
                        p.setColor(ContextCompat.getColor(context, colorRes));

                        RectF rectF = new RectF(itemView.getRight() + dX,
                                itemView.getTop(),
                                itemView.getRight(),
                                itemView.getBottom());
                        c.drawRect(rectF, p);
                    }
                } else {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }
        });
    }

    public interface OnLeftSwipeListener {
        void onLeftSwipe(int adapterPosition);
    }
}
