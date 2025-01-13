// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.GrabberConstants;
import frc.robot.Constants.GrabberConstants.PivotStates;

public class GrabberSubsystem extends SubsystemBase {

    private final CANSparkMax pivotMotor;
    private final CANSparkMax intakeMotor;
    private final RelativeEncoder pivotEncoder;
    private final RelativeEncoder intakeEncoder;
    private final PIDController pid;
    private double currentState;
    private PivotStates state;
    private double desiredSetPoint;

    /** Creates a new GrabberSubsystem instance. */
    public GrabberSubsystem(PivotStates state) {
        // Initializing motors and encoders for both pivot and intake systems
        pivotMotor = new CANSparkMax(1, CANSparkMax.MotorType.kBrushless);
        intakeMotor = new CANSparkMax(2, CANSparkMax.MotorType.kBrushless);
        
        pivotEncoder = pivotMotor.getEncoder();
        intakeEncoder = intakeMotor.getEncoder();
        desiredSetPoint = state.getPivotSetPoint();
        // Initialize PID controller for precise control of pivot motor
        pid = new PIDController(0.001, 0.0, 0.0);  // Adjust with correct PID constants
        
        
    }

    // Method to control the pivot motor speed
    public void setPivotMotor(double speed){
        pivotMotor.set(speed);
      }
    public void setIntakeMotor(double speed){
        intakeMotor.set(speed);
    }

      public double getPivotPosition(){
        return pivotEncoder.getPosition();
      }
    
      public double getPivotVelocity(){
        return pivotEncoder.getVelocity();  
      }
    
      public double getDesiredSetPoint(){
        return desiredSetPoint;
      }
      
      public void setState(PivotStates tempState){
        state = tempState;
        desiredSetPoint = state.getPivotSetPoint();
      }
      public double getCurrentPIDOutput(){
        return pid.calculate(pivotEncoder.getPosition(), desiredSetPoint);
      }

     @Override
  public void periodic() {
    // This method will be called once per scheduler run
    pivotMotor.set(getCurrentPIDOutput());
  }
}