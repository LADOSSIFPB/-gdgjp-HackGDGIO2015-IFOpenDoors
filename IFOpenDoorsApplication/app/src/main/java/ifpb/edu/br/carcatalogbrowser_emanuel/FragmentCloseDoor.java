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

import java.util.concurrent.ExecutionException;

import ifpb.edu.br.util.Constantes;

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

    // Used to store result from free-form speech
    private String mNotes;

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
                String message = "";
                CommunicationAsyncTask communicationAsyncTask =
                        new CommunicationAsyncTask(getActivity(), Constantes.CLOSE_DOOR, getRow() + 1);
                try {
                    message = communicationAsyncTask.execute().get();
                } catch (InterruptedException e) {

                } catch (ExecutionException e) {

                }
                if (!message.equals(""))
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Comunicacao Falhou.", Toast.LENGTH_SHORT).show();
            }
        });

        mConfirmationView.setListener(this);
    }

    @Override
    public void onTimerFinished(View view) {
        final Activity activity = getActivity();
        if (activity == null) {
            // Fragment do not belong anymore to activity.
            return;
        }

        mConfirmationView.reset();
        mConfirmationView.setImageResource(R.drawable.ic_close_door);
        Toast.makeText(getActivity(), mNotes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimerSelected(View view) {
        mConfirmationView.reset();
    }
}
