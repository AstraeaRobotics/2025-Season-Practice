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

  private WinchStates currentState;
  private double setpoint;

  public WinchSubsystem() {
    // Initialize motor and encoder
    motor = new CANSparkMax(Constants.WinchConstants.kWinchPort, MotorType.kBrushless);
    encoder = motor.getEncoder();

    // Configure PID controller
    pidController = new PIDController(Constants.WinchConstants.KP, Constants.WinchConstants.KI,Constants.WinchConstants.KD);

    // Set default state and target position
    
    configureEncoder();
  }

  // Retrieve the current state
  public WinchStates getCurrentState() {
    return currentState;
  }

  // Retrieve the target setpoint
  public double getSetpoint() {
    return setpoint;
  }

  // Get the current position from the encoder
  public double getCurrentPosition() {
    return encoder.getPosition();
  }

  // Update the current state and recalculate the setpoint
  public void updateState(WinchStates newState) {
    currentState = newState;
    setpoint = currentState.getWinchSetPoint();
    pidController.setSetpoint(setpoint);
  }

  // Calculate the PID output
  public double calculatePIDOutput() {
    double pidOutput = pidController.calculate(getCurrentPosition(), setpoint);
    SmartDashboard.putNumber("Winch PID Output", pidOutput);
    return pidOutput;
  }

  // Directly control the motor speed
  public void setMotorSpeed(double speed) {
    motor.set(speed);
  }

  // Reset encoder position and configure settings
  private void configureEncoder() {
    encoder.setPosition(0);
  }

  @Override
  public void periodic() {
    // Publish encoder position and apply PID control
    SmartDashboard.putNumber("Winch Encoder Position", getCurrentPosition());
    setMotorSpeed(calculatePIDOutput());
  }
}
