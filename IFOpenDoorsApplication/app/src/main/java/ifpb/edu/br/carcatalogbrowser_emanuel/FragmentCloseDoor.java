package ifpb.edu.br.carcatalogbrowser_emanuel;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.view.DelayedConfirmationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentCloseDoor extends Fragment
        implements DelayedConfirmationView.DelayedConfirmationListener {

    private int row;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    private DelayedConfirmationView mConfirmationView;
    private TextView mTextView;
    private boolean mIsAnimating = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate and customize the UI from its XML layout definition
        final View view = inflater.inflate(R.layout.fragment_view, container, false);
        mConfirmationView = (DelayedConfirmationView)
                view.findViewById(R.id.delayed_confirm);
        mConfirmationView.setImageDrawable(getResources().
                getDrawable(R.drawable.ic_close_door));
        mTextView = (TextView) view.findViewById(R.id.label);
        mTextView.setText("Fechar Porta");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getRow()) {
                    case 0:
                        Toast.makeText(getActivity(), "Fechando Laboratorio de Informatica",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "Fechando Laboratorio de Mineracao",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "Fechando Laboratorio de Quimica",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity(), "Fechando Laboratorio de Matematica",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        mConfirmationView.setListener(this);
    }

    @Override
    public void onTimerFinished(View view) {
        mIsAnimating = false;
        final Activity activity = getActivity();
        if (activity == null) {
            // Fragment do not belong anymore to activity.
            return;
        }

        // Starting the confirmation screen
        Intent intent = new Intent(activity, ConfirmationActivity.class);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                ConfirmationActivity.SUCCESS_ANIMATION);
        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "Scheduled");
        startActivity(intent);

        mConfirmationView.reset();
    }

    @Override
    public void onTimerSelected(View view) {
        mConfirmationView.reset();
    }
}
