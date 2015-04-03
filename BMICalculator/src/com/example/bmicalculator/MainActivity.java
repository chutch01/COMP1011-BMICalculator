package com.example.bmicalculator;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;


public class MainActivity extends Activity {
	
	private double _BMIFinal;
	private double _weight;
	private double _height;
	
	private TextView _weightTextView;
	private TextView _heightTextView;
	private EditText _weightEditText;
	private EditText _heightEditText;
	private EditText _BMICalculationEditText;
	Button calculateButton;
	Button unitsButton;
	
	//set to true of metric (kg, m), false if imperial *(lbs, ft)
	boolean metricImperial = true;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        this._heightTextView = (TextView) findViewById(R.id.heightTextView);
        this._weightTextView = (TextView) findViewById(R.id.weightTextView);
        this._weightEditText = (EditText) findViewById(R.id.weightEditText);
        this._heightEditText = (EditText) findViewById(R.id.heightEditText);
        this._BMICalculationEditText = (EditText) findViewById(R.id.BMICalculationEditText);
        unitsButton = (Button) findViewById(R.id.unitsButton);
        calculateButton = (Button)  findViewById(R.id.calculateButton);
       
        //event to change the units when the units button is clicked
        OnClickListener listenerUnitsButton = new OnClickListener(){
        	@Override
        	public void onClick(View v){
        	if(metricImperial){
        		unitsButton.setText("Imperial");
        		metricImperial = false;
        		_weightTextView.setText("Weight (lbs):");
        		_heightTextView.setText("Height (in):");
        	}//end if
        	else{
        		unitsButton.setText("Metric");
        		metricImperial = true;
        		_weightTextView.setText("Weight (kg):");
        		_heightTextView.setText("Height (m):");
        	}//end else
        	}//end on click
			
        };//ends listener units button
        
        OnClickListener listenerCalculateButton = new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		if (metricImperial){
        			_BMIFinal = _weight / (_height * _height);
        			_BMICalculationEditText.setText(String.format("%0.2f", _BMIFinal));
        			
        		}
        		else{
        			_BMIFinal = (_weight * 703) / (_height * _height);
        			_BMICalculationEditText.setText(String.format("%0.2f", _BMIFinal));
        		}
        	}
        };//ends listenerCalculateButton
        //assign the listeners to the buttons
        unitsButton.setOnClickListener(listenerUnitsButton);
        calculateButton.setOnClickListener(listenerCalculateButton);
        this._heightEditText.addTextChangedListener(EditTextWatcher);
        this._weightEditText.addTextChangedListener(EditTextWatcher);
        
        
    }//end onCreate
    
    
    
    
    
//private methods
    private TextWatcher EditTextWatcher = new TextWatcher(){
 public void onTextChanged(CharSequence s, int start, int before, int count){
	 try{
		 if(_heightEditText.getText().hashCode() == s.hashCode())
		 {
			 _height = Double.parseDouble(s.toString());
		 }//if
		 else if (_weightEditText.getText().hashCode() == s.hashCode())
		 {
			 _weight = Double.parseDouble(s.toString());
		 }//else if
	 }//try
	 catch(NumberFormatException e){
		 _height = 0;
		 _weight = 0;
	 }//end catch
	 }//end onText Changed
 
 @Override
 public void afterTextChanged(Editable s)
 {
	 
 }
 
 @Override
 public void beforeTextChanged(CharSequence s , int start, int count, int after)
 {
	 
 }
    };
    
 @Override
 public boolean onCreateOptionsMenu(Menu menu){
	 getMenuInflater().inflate(R.menu.main, menu);
	 return true;
 }
 
 @Override
 public boolean onOptionsItemSelected(MenuItem item){
	 int id = item.getItemId();
	 if(id == R.id.action_settings){
		 return true;
	 }
	 return super.onOptionsItemSelected(item);
 };

}

 
 

	 
 


 
 
    
    
    

