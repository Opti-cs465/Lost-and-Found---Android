package cs465.illinois.lost_and_found.ui.slideshow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ycuwq.datepicker.date.DatePickerDialogFragment;

import cs465.illinois.lost_and_found.R;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        ImageButton addLocation = (ImageButton) root.findViewById(R.id.addLocation);
        final EditText result = (EditText) root.findViewById(R.id.addLoc);

        addLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(getActivity());
                //View promptsView = li.inflate(R.layout.prompts);
                View promptsView = li.inflate(R.layout.prompts, container, false);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView.findViewById(R.id.Input);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        Log.d("Output",result.getText().toString());
                                        if (result.getText().toString().equals(""))
                                            result.setText(userInput.getText());

                                        else
                                            result.setText(result.getText().toString() + ", " + userInput.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

//        ImageButton addMainFeature = (ImageButton) root.findViewById(R.id.addMainFeature);
//        final EditText mainFeature = (EditText) root.findViewById(R.id.mainFeature);

//        addMainFeature.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                // get prompts.xml view
//                LayoutInflater li = LayoutInflater.from(getActivity());
//                //View promptsView = li.inflate(R.layout.prompts);
//                View mFView = li.inflate(R.layout.add_main_feature, container, false);
//
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                        getActivity());
//
//                // set prompts.xml to alertdialog builder
//                alertDialogBuilder.setView(mFView);
//
//                final EditText userInput = (EditText) mFView.findViewById(R.id.Input);
//
//                // set dialog message
//                alertDialogBuilder
//                        .setCancelable(false)
//                        .setPositiveButton("OK",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog,int id) {
//                                        // get user input and set it to result
//                                        // edit text
//                                        Log.d("Output",mainFeature.getText().toString());
//                                        if (mainFeature.getText().toString().equals(""))
//                                            mainFeature.setText(userInput.getText());
//
//                                        else
//                                            mainFeature.setText(mainFeature.getText().toString() + ", " + userInput.getText());
//                                    }
//                                })
//                        .setNegativeButton("Cancel",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog,int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//
//                // create alert dialog
//                AlertDialog alertDialog = alertDialogBuilder.create();
//
//                // show it
//                alertDialog.show();
//
//            }
//        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.dateLayout);
        ImageButton mClickAdd2 = (ImageButton) view.findViewById(R.id.add2);
        final TextView dateTv = view.findViewById(R.id.addDate);
        mClickAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
                datePickerDialogFragment.setOnDateChooseListener(new DatePickerDialogFragment.OnDateChooseListener() {
                    @Override
                    public void onDateChoose(int year, int month, int day) {
                        Context context = getActivity().getApplicationContext();
//                    Toast.makeText(context, year + "-" + month + "-" + day, Toast.LENGTH_SHORT).show();
                        dateTv.setText(year + "-" + month + "-" + day);
                    }
                });
                datePickerDialogFragment.show(SlideshowFragment.this.getFragmentManager(), "DatePickerDialogFragment");
            }
        });

        ImageButton mClickAdd3 = (ImageButton) view.findViewById(R.id.add3);
        final TextView cateTv = view.findViewById(R.id.addCategory);
        mClickAdd3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View root) {
                final String [] categories ={"Phone","Earphone","Bottle","Book/Notebook","Card","Bag"};
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose category");
                builder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
                    // 第二个参数为默认选中项 在这里设为第一项
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "The item belongs to "+categories[which], Toast.LENGTH_SHORT).show();
                        cateTv.setText(categories[which]);
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getActivity(), "您已经提交您的选择", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();

            }
        });
    }
}