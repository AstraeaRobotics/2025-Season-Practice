// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.WinchConstants;
import frc.robot.Constants.WinchConstants.WinchStates;

public class WinchSubsystem extends SubsystemBase {

  private final CANSparkMax motor;
  private final RelativeEncoder encoder;
  private final PIDController pidController;
  private double desiredSetPoint;
  private WinchStates currentState;
  

  public WinchSubsystem() {
    // Initialize motor and encoder
    motor = new CANSparkMax(Constants.WinchConstants.kWinchPort, MotorType.kBrushless);
    encoder = motor.getEncoder();

    // Configure PID controller
    pidController = new PIDController(Constants.WinchConstants.KP, Constants.WinchConstants.KI,Constants.WinchConstants.KD);

    // Set default state and target position
    
   // configureEncoder();
  }
  public void setMotor( double speed){
    motor.set(speed);
  }
  public void setState(WinchStates tempState){
    currentState = tempState;
    desiredSetPoint = currentState.getWinchSetPoint();
  }
  public double getPosition(){
    return encoder.getPosition();
  }
  public double getDesiredSetPoint(){
return desiredSetPoint;
  }
  public double getCurrentPIDOutput(){
    return pidController.calculate(encoder.getPosition(), desiredSetPoint);
  }
  
  // Reset encoder position and configure settings
  

   @Override
  public void periodic() {
    // This method will be called once per scheduler run
    motor.set(getCurrentPIDOutput());
  }
}
