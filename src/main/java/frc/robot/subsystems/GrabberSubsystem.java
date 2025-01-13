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

public class GrabberSubsystem extends SubsystemBase {

    private final CANSparkMax pivotMotor;
    private final CANSparkMax intakeMotor;
    private final RelativeEncoder pivotEncoder;
    private final RelativeEncoder intakeEncoder;
    private final PIDController pivotPidController;

    private GrabberConstants.GrabberStates currentState;
    private double targetPosition;

    /** Creates a new GrabberSubsystem instance. */
    public GrabberSubsystem() {
        // Initializing motors and encoders for both pivot and intake systems
        pivotMotor = new CANSparkMax(1, CANSparkMax.MotorType.kBrushless);
        intakeMotor = new CANSparkMax(2, CANSparkMax.MotorType.kBrushless);
        
        pivotEncoder = pivotMotor.getEncoder();
        intakeEncoder = intakeMotor.getEncoder();

        // Initialize PID controller for precise control of pivot motor
        pivotPidController = new PIDController(0.001, 0.0, 0.0);  // Adjust with correct PID constants
        
        // Set initial grabber state
        currentState = GrabberConstants.GrabberStates.kGround;
        targetPosition = currentState.getGrabberSetpoint();

        // Reset encoder positions for accurate feedback
        resetEncoders();
    }

    // Method to control the pivot motor speed
    public void controlPivotMotor(double speed) {
        pivotMotor.set(speed);
    }

    // Method to control the intake motor speed
    public void controlIntakeMotor(double speed) {
        intakeMotor.set(speed);
    }

    // Update grabber's state and adjust setpoint based on new state
    public void updateState(GrabberConstants.GrabberStates newState) {
        currentState = newState;
        targetPosition = currentState.getGrabberSetpoint();
    }

    // Get current grabber state
    public GrabberConstants.GrabberStates getCurrentState() {
        return currentState;
    }

    // Calculate PID output to control pivot motor based on encoder feedback
    private double calculatePivotPID() {
        return pivotPidController.calculate(pivotEncoder.getPosition(), targetPosition);
    }

    // Apply the PID output to the pivot motor
    private void applyPIDControl() {
        pivotMotor.set(calculatePivotPID());
    }

    // Reset encoders to 0 for accurate position tracking
    private void resetEncoders() {
        pivotEncoder.setPosition(0);
        intakeEncoder.setPosition(0);
    }

    @Override
    public void periodic() {
        // Display encoder positions on SmartDashboard for real-time feedback
        SmartDashboard.putNumber("Pivot Encoder Position", pivotEncoder.getPosition());
        SmartDashboard.putNumber("Intake Encoder Position", intakeEncoder.getPosition());
        
        // Apply PID control to the pivot motor
        applyPIDControl();
    }
}
