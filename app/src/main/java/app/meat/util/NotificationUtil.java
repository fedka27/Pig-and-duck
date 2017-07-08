package app.meat.util;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import app.meat.R;

import static android.support.design.widget.Snackbar.LENGTH_INDEFINITE;
import static android.support.design.widget.Snackbar.LENGTH_LONG;

public class NotificationUtil {
    private int DURATION_LONG = 3000;
    private int DURATION_INDEFINITE = LENGTH_INDEFINITE;
    @NonNull
    private Context context;
    @NonNull
    private View view;
    private Snackbar snackbar;

    public NotificationUtil(@NonNull Context context,
                            @NonNull View view) {
        this.context = context;
        this.view = view;
    }

    public void showError(String message) {
        showSnackBarNotification(message, DURATION_LONG, R.color.error_red);
    }

    public void showError(@StringRes int res) {
        showSnackBarNotification(context.getString(res), DURATION_LONG, R.color.error_red);
    }

    public void showProgress() {
        showProgress(R.string.fragment_base_processing);
    }

    public void showProgress(@StringRes int res) {
        showSnackBarNotification(context.getString(res), DURATION_INDEFINITE, R.color.progress_blue);
    }

    public void showToast(int res) {
        showSnackBarNotification(context.getString(res), LENGTH_LONG, R.color.toast_green_dark);
    }

    public void showToast(String message) {
        showSnackBarNotification(message, LENGTH_LONG, R.color.toast_green_dark);
    }

    private void showSnackBarNotification(String message, int duration, @ColorRes int color) {
        snackbar = Snackbar.make(view, message, duration);

        snackbar.getView()
                .setBackgroundColor(ContextCompat.getColor(context, color));

        snackbar.show();
    }

    public void hideProgress() {
        if (snackbar != null) {
            snackbar.dismiss();
        }
    }
}
