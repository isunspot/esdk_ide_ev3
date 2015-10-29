package com.huawei.controldemo;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import com.huawei.esdk.ev3.common.ChallengeResultCmd;
import com.huawei.esdk.ev3.common.ExitSignalCmd;
import com.huawei.esdk.ev3.common.FoundPositionCmd;
import com.huawei.esdk.ev3.common.PatrolCmd;
import com.huawei.esdk.ev3.common.RobotMoveCmd;
import com.huawei.esdk.ev3.common.RobotMoveCmd.Command;
import com.huawei.esdk.ev3.common.SendGiftCmd;
import com.huawei.utils.Tools;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText socketIp;
	private EditText socketPort;
	private Button connBtn;
	private Button releaseBtn;
	private Button patrolBtn;
	private Button stopPatrolBtn;
	private Button foundPosBtn;
	private Button sendGiftBtn;
	private Button reviewRetBtn;
	
	private Button forwardBtn,backBtn,leftBtn,rightBtn,stopBtn;
	private Button seat1,seat2,seat3,seat4,seat5,seat6;
	private Button seat7,seat8,seat9,seat10,seat11,seat12;
	private TextView seatTip;
	
	private RadioGroup radioGroup;
//	private String rbVal = "success";//单选按钮
	private String posVal = "0";
	private Socket socket = null;
	ObjectOutputStream oos = null;
	private  String ipAddr = "";
	private int port;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		
		
		socketIp = (EditText) findViewById(R.id.socketIp);
		socketPort = (EditText) findViewById(R.id.socketPort);
		socketIp.setText("192.168.1.5");
		socketPort.setText("9058");
		
		connBtn = (Button) findViewById(R.id.connBtn);
		releaseBtn = (Button) findViewById(R.id.releaseBtn);
		
		forwardBtn = (Button) findViewById(R.id.forwardBtn);
		backBtn = (Button) findViewById(R.id.backBtn);
		leftBtn = (Button) findViewById(R.id.leftBtn);
		rightBtn = (Button) findViewById(R.id.rightBtn);
		stopBtn = (Button) findViewById(R.id.stopBtn);
		
		seat1 = (Button) findViewById(R.id.seat1);
		seat2 = (Button) findViewById(R.id.seat2);
		seat3 = (Button) findViewById(R.id.seat3);
		seat4 = (Button) findViewById(R.id.seat4);
		seat5 = (Button) findViewById(R.id.seat5);
		seat6 = (Button) findViewById(R.id.seat6);
		seat7 = (Button) findViewById(R.id.seat7);
		seat8 = (Button) findViewById(R.id.seat8);
		seat9 = (Button) findViewById(R.id.seat9);
		seat10 = (Button) findViewById(R.id.seat10);
		seat11 = (Button) findViewById(R.id.seat11);
		seat12 = (Button) findViewById(R.id.seat12);
		
		seatTip = (TextView) findViewById(R.id.seatTip);
		
		patrolBtn = (Button) findViewById(R.id.patrolBtn);
		stopPatrolBtn = (Button) findViewById(R.id.stopPatrolBtn);
		foundPosBtn = (Button) findViewById(R.id.foundPosition);
		sendGiftBtn = (Button) findViewById(R.id.sendGiftBtn);
		reviewRetBtn = (Button) findViewById(R.id.reviewRetBtn);
		radioGroup = (RadioGroup) findViewById(R.id.chooseRG);
		
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				 //获取变更后的选中项的ID
//                int radioButtonId = group.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
//                RadioButton rb = (RadioButton)MainActivity.this.findViewById(radioButtonId);
//                rbVal = rb.getText().toString();
			}
		});
		 
		 connBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!checkIpPort()){
					Toast.makeText(MainActivity.this,"ip or port error",
		                    Toast.LENGTH_SHORT).show();
					return;
				}
				
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							socket = new Socket(ipAddr, port);
							oos = new ObjectOutputStream(socket.getOutputStream());
//							Toast.makeText(MainActivity.this,"connect robot",
//				                    Toast.LENGTH_SHORT).show();
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				t.start();
			}
		}); 
		 
		 
		 releaseBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				seatTip.setText("");
				if(oos!=null&&socket!=null){
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							try {
								ExitSignalCmd Exitmsg = new ExitSignalCmd();
								oos.writeObject(Exitmsg);//巡逻
								oos.flush();
								try {
									TimeUnit.SECONDS.sleep(2);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								oos.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							try {
								socket.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}).start();
				}
			}
		});
		 
		stopPatrolBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if (null != oos){
					new Thread(new Runnable() {
						public void run() {
							try {
								PatrolCmd msg = new PatrolCmd();
								msg.setStartFlag("N");
								oos.writeObject(msg);//巡逻
								oos.flush();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}).start();
				}
			}
		});
		
		foundPosBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if (null != oos){
					new Thread(new Runnable() {
						public void run() {
							try {
								FoundPositionCmd msg = new FoundPositionCmd();
								oos.writeObject(msg);//
								oos.flush();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}).start();
				}
			}
		});
		
		sendGiftBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if (null != oos){
					new Thread(new Runnable() {
						public void run() {
							try {
								SendGiftCmd msg = new SendGiftCmd();
								oos.writeObject(msg);//发送
								oos.flush();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}).start();
				}
			}
		});
		
		
		 patrolBtn.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				if(oos!=null){
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							
							try {
								PatrolCmd msg = new PatrolCmd();
								msg.setStartFlag("Y");
								oos.writeObject(msg);//巡逻
								oos.flush();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}).start();
					
				}
			}
		});
		 
		 reviewRetBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(oos!=null){
						new Thread(new Runnable() {
							@Override
							public void run() {
								
							if("0".equals(posVal)){
								Toast.makeText(MainActivity.this,"plz choose seat!",
					                    Toast.LENGTH_SHORT).show();
								return;
							}
							
							ChallengeResultCmd msg = new ChallengeResultCmd();
							
							//TODO
//							int radioButtonId = group.getCheckedRadioButtonId();
			                //根据ID获取RadioButton的实例
			                RadioButton rb = (RadioButton)MainActivity.this.findViewById(R.id.succBtn);
			                String rbVal;
							if(rb.isChecked()) {
								rbVal = "Y";
							} else {
								rbVal = "N";
							}
							msg.setPosition(posVal);
							msg.setResult(rbVal);
							Log.d("ChallengeResultCmd", "position:"+posVal+" , success : "+ rbVal);
							try {
								oos.writeObject(msg);
								oos.flush();
//								Toast.makeText(MainActivity.this,"reviewResult position:"+posVal+",ret:"+rbVal,
//					                    Toast.LENGTH_SHORT).show();
							} catch (IOException e) {
								e.printStackTrace();
							}
							}
						}).start();
					}
				}
			});
		 
		 
		 forwardBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(oos!=null){
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							
							try {
								RobotMoveCmd msg = new RobotMoveCmd();
								msg.setCommand(Command.Forward);
								oos.writeObject(msg);
								oos.flush();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}).start();
					
				}
			}
		});
		 
		 backBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(oos!=null){
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								
								try {
									RobotMoveCmd msg = new RobotMoveCmd();
									msg.setCommand(Command.Backward);
									oos.writeObject(msg);
									oos.flush();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}).start();
						
					}
				}
			});
		 
		 leftBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(oos!=null){
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								
								try {
									RobotMoveCmd msg = new RobotMoveCmd();
									msg.setCommand(Command.Left);
									oos.writeObject(msg);
									oos.flush();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}).start();
						
					}
				}
			});
		 
		 rightBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(oos!=null){
						new Thread(new Runnable() {
							@Override
							public void run() {
								
								try {
									RobotMoveCmd msg = new RobotMoveCmd();
									msg.setCommand(Command.Right);
									oos.writeObject(msg);
									oos.flush();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}).start();
						
					}
				}
			});
		 
		 stopBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(oos!=null){
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								
								try {
									RobotMoveCmd msg = new RobotMoveCmd();
									msg.setCommand(Command.Stop);
									oos.writeObject(msg);
									oos.flush();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}).start();
						
					}
				}
			});
		 
		 
		 seat1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				posVal = "1";
				seatTip.setText("current seat: 1号");
			}
		});
		 seat2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					posVal = "2";
					seatTip.setText("current seat: 2号");
				}
			});
		 seat3.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					posVal = "3";
					seatTip.setText("current seat: 3号");
				}
			});
		 seat4.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					posVal = "4";
					seatTip.setText("current seat: 4号");
				}
			});
		 seat5.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					posVal = "5";
					seatTip.setText("current seat: 5号");
				}
			});
		 seat6.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					posVal = "6";
					seatTip.setText("current seat: 6号");
				}
			});
		 seat7.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					posVal = "7";
					seatTip.setText("current seat: 7号");
				}
			});
		 seat8.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					posVal = "8";
					seatTip.setText("current seat: 8号");
				}
			});
		 seat9.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					posVal = "9";
					seatTip.setText("current seat: 9号");
				}
			});
		 seat10.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					posVal = "10";
					seatTip.setText("current seat: 10号");
				}
			});
		 seat11.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					posVal = "11";
					seatTip.setText("current seat: 11号");
				}
			});
		 seat12.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					posVal = "12";
					seatTip.setText("current seat: 12号");
				}
			});
		 
		 
	}
	
	private boolean checkIpPort() {
		ipAddr = socketIp.getText().toString();
		String portStr = socketPort.getText().toString();
	    if (Tools.isEmpty(ipAddr) || Tools.isEmpty(portStr))
        {
            return false;
        }
	    port = Integer.parseInt(portStr);
	    if(!Tools.checkIP(ipAddr)||!Tools.checkPort(port)){
	    	  return false;
	    }
	    return true;
	}
	
	
	
}
