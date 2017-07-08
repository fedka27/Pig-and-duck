package app.meat.view.adapter.divider;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class ItemDarkDivider extends RecyclerView.ItemDecoration {

    private Drawable divider;

    public ItemDarkDivider(Drawable divider) {
        this.divider = divider;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int right = parent.getWidth() - parent.getPaddingRight();
        int left = parent.getLeft();

        //childCount - отображает видимое количество элементво;
        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {

            View child = parent.getChildAt(i);
            //targetPosition - отображает позицию элемента по списку;
            int targetPosition = parent.getChildAdapterPosition(child);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        outRect.set(0, 1, 0, 1);
//    }
}