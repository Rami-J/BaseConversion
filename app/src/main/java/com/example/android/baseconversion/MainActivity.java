package com.example.android.baseconversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

	private Spinner spinner1;
	private Spinner spinner2;
	private String selectedItem1 = "Decimal";
	private String selectedItem2 = "Decimal";
	private ArrayAdapter<CharSequence> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		arrayAdapter = ArrayAdapter.createFromResource(this,
				R.array.base_choices, R.layout.spinner_textview_align);

		spinner1 = (Spinner) findViewById(R.id.spinner);
		spinner2 = (Spinner) findViewById(R.id.spinner2);

		arrayAdapter.setDropDownViewResource(R.layout.spinner_textview_align);
		spinner1.setAdapter(arrayAdapter);
		spinner2.setAdapter(arrayAdapter);

		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				spinner1.setOnItemSelectedListener(this);
				spinner2.setOnItemSelectedListener(this);

				if (parent.getId() == R.id.spinner)
				{
					selectedItem1 = (String) parent.getItemAtPosition(position);
					//Log.i("MainActivity", selectedItem1);
				}
				else if (parent.getId() == R.id.spinner2)
				{
					selectedItem2 = (String) parent.getItemAtPosition(position);
					//Log.i("MainActivity", selectedItem2);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

	}

	public void calculateResult(View v)
	{
		EditText editText = (EditText) findViewById(R.id.editText);
		String resultString = "", hexString;
		int num;

		// statement to check if the text field is empty, preventing runtime errors
		if (editText.getText().length() == 0)
		{
			return;
		}

		// handles exceptions when the user inputs characters other than A-F and 0-9
		if (selectedItem1.equals("Hexadecimal"))
		{
			try {
				Integer.parseInt(editText.getText().toString(), 16);
			} catch (NumberFormatException e) {
				displayResult("Invalid Input");
				return;
			}
		}
		// handles exceptions when the user inputs characters other than 0-9
		else
		{
			try {
				Integer.parseInt(editText.getText().toString());
			} catch (NumberFormatException e) {
				displayResult("Invalid Input");
				return;
			}
		}

		if (selectedItem1.equals("Decimal"))
		{
			// receives the currently inputted text from the editText widget
			num = Integer.parseInt(editText.getText().toString());
			resultString = BaseConverter.convertDecimal(num, selectedItem2);
		}
		else if (selectedItem1.equals("Binary"))
		{
			// checks if the input is not a binary number
			if (!isBinaryNumber(editText.getText().toString()))
			{
				displayResult("Invalid Input");
				return;
			}
			num = Integer.parseInt(editText.getText().toString());
			resultString = BaseConverter.convertBinary(num, selectedItem2);
		}
		else if (selectedItem1.equals("Octal"))
		{
			// checks if the input is not an octal number
			if (!isOctalNumber(editText.getText().toString()))
			{
				displayResult("Invalid Input");
				return;
			}
			num = Integer.parseInt(editText.getText().toString());
			resultString = BaseConverter.convertOctal(num, selectedItem2);
		}
		else if (selectedItem1.equals("Hexadecimal"))
		{
			hexString = editText.getText().toString();
			resultString = BaseConverter.convertHexadecimal(hexString, selectedItem2);
		}

		displayResult(resultString);
	}

	/**
	 * @param num string representing a number
	 * @return true if the number is binary, false otherwise
	 */
	public boolean isBinaryNumber(String num)
	{
		int i;
		char ch;

		for (i = 0; i < num.length(); i++)
		{
			ch = num.charAt(i);
			if (ch != '1' && ch != '0')
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * @param num string representing a number
	 * @return true if the number is octal, false otherwise
	 */
	public boolean isOctalNumber(String num)
	{
		int i, val;
		char ch;

		for (i = 0; i < num.length(); i++)
		{
			ch = num.charAt(i);
			val = Integer.parseInt(ch + "");

			if (val >= 8)
			{
				return false;
			}
		}

		return true;
	}

	public void displayResult(String resultString)
	{
		TextView resultTextView = (TextView) findViewById(R.id.result);
		resultTextView.setText(resultString);
	}
}
