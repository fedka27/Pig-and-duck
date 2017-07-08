package app.meat.view.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import app.meat.util.NotificationUtil;
import butterknife.ButterKnife;

public abstract class BaseDialogFragment extends DialogFragment {
    protected NotificationUtil notificationUtil;
    private Snackbar snackbar;
    private FragmentManager fragmentManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentManager = getFragmentManager();
    }

    @NonNull
    @Override
    final public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        notificationUtil = new NotificationUtil(getContext(), view);
    }

    @Nullable
    @Override
    public abstract View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);


    protected void replaceFragment(@IdRes int containerId,
                                   BaseFragment fragment,
                                   boolean addToBackStack) {
        showFragment(containerId, fragmentManager, fragment, addToBackStack);
    }

    protected void replaceFragment(@IdRes int containerId,
                                   FragmentManager fragmentManager,
                                   BaseFragment fragment,
                                   boolean addToBackStack) {
        showFragment(containerId, fragmentManager, fragment, addToBackStack);
    }

    protected void replaceFragment(@IdRes int containerId, BaseFragment fragment) {
        showFragment(containerId, fragmentManager, fragment, false);
    }


    private void showFragment(@IdRes int containerId,
                              FragmentManager fragmentManager,
                              BaseFragment fragment,
                              boolean addToBackStack) {
        try {
            ((FrameLayout) getActivity().findViewById(containerId)).removeAllViews();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(containerId, fragment, fragment.getClass().getSimpleName());
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null);
            }
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
