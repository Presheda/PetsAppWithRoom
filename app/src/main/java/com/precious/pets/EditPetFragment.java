package com.precious.pets;


import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.precious.pets.databinding.FragmentEditPetBinding;
import com.precious.pets.databinding.ListPetBinding;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditPetFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {

    FragmentEditPetBinding listPetBinding;
    private int petId;


    public EditPetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         listPetBinding = FragmentEditPetBinding.inflate(inflater);

        setupAdapterAndSpinner();
        setError();


        listPetBinding.buttonSave.setOnClickListener((view) -> {
            boolean nonIsEmpty = validateFields();
            if(nonIsEmpty){
                savePet();
            }
        });

        if(petId == -1){
            listPetBinding.buttonSave.setText("Save");
        } else {
            listPetBinding.buttonSave.setText("Update");
        }

         return listPetBinding.getRoot();


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();

        if(bundle != null){
            petId = bundle.getInt(MainActivity.PET_ID);
            loadPet(petId);
        } else {
            petId = -1;
        }





    }

    private boolean validateFields() {

        String petName = listPetBinding.textInputEditTextName.getText().toString();
        String petType = listPetBinding.textInputEditTextType.getText().toString();
        String petAge = listPetBinding.textInputEditTextAge.getText().toString();


        return !petName.isEmpty() && !petType.isEmpty() && !petAge.isEmpty();
    }

    private void savePet() {
        String name = listPetBinding.textInputEditTextName.getText().toString();
        String breed = listPetBinding.textInputEditTextType.getText().toString();
        int age = Integer.valueOf(listPetBinding.textInputEditTextAge.getText().toString());
        int sex = listPetBinding.spinnerSex.getSelectedItemPosition();
        PetHolder petHolder = new PetHolder(name, breed, sex, age);
        PetViewModel petViewModel = ViewModelProviders.of(this).get(PetViewModel.class);

        if(petId == -1){
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    petViewModel.insertPet(petHolder);
                }
            });


        } else {
            petHolder.setId(petId);
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    petViewModel.updatePet(petHolder);
                }
            });

        }

        getDialog().dismiss();

    }

    private void loadPet(int petId) {
        PetViewModel petViewModel = ViewModelProviders.of(this).get(PetViewModel.class);

      AppExecutors.getInstance().diskIO().execute(new Runnable() {
          @Override
          public void run() {
              petViewModel.getPetById(petId).observe(EditPetFragment.this, new Observer<PetHolder>() {
                  @Override
                  public void onChanged(@Nullable PetHolder petHolder) {
                      initPetFields(petHolder);
                  }
              });
          }
      });
    }

    private void initPetFields(PetHolder petHolder) {
        listPetBinding.spinnerSex.setSelection(petHolder.getPetSex());
        listPetBinding.textInputEditTextName.setText(petHolder.getPetName());
        listPetBinding.textInputEditTextType.setText(petHolder.getPetBreed());
        listPetBinding.textInputEditTextAge.setText(String.valueOf(petHolder.getPetAge()));

    }


    private void setupAdapterAndSpinner() {

        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.pet_sex_array, android.R.layout.simple_spinner_item);
        listPetBinding.spinnerSex.setOnItemSelectedListener(this);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listPetBinding.spinnerSex.setAdapter(arrayAdapter);
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Dialog dialog = super.onCreateDialog(savedInstanceState);
//        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//
//        return dialog;
//    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
         //   dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    /**
     * Enforces that the editText field are filled correctly before saving
     * by displaying error messsages
     */
    public void setError() {
        listPetBinding.textInputEditTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String petName = listPetBinding.textInputEditTextName.getText().toString();
                if (petName.isEmpty()) {
                    listPetBinding.textInputLayoutName.setErrorEnabled(true);
                    listPetBinding.textInputLayoutName.setError(getResources().getString(R.string.layout_error_message));
                } else {
                    listPetBinding.textInputLayoutName.setErrorEnabled(false);
                    listPetBinding.textInputLayoutName.setError(null);
                }

            }
        });
        listPetBinding.textInputEditTextType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    listPetBinding.textInputLayoutType.setErrorEnabled(true);
                    listPetBinding.textInputLayoutType.setError(getResources().getString(R.string.layout_error_message));
                } else {
                    listPetBinding.textInputLayoutType.setErrorEnabled(false);
                    listPetBinding.textInputLayoutType.setError(null);
                }
            }
        });
        listPetBinding.textInputEditTextAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    listPetBinding.textInputLayoutAge.setErrorEnabled(true);
                    listPetBinding.textInputLayoutAge.setError(getResources().getString(R.string.layout_error_message));
                } else {
                    listPetBinding.textInputLayoutAge.setErrorEnabled(false);
                    listPetBinding.textInputLayoutAge.setError(null);
                }
            }
        });


    }

}
