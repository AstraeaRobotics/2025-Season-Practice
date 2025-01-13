// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Winch;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.GrabberConstants.GrabberStates;
import frc.robot.Constants.WinchConstants.WinchStates;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.WinchSubsystem;

package frc.robot.commands.Winch;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.GrabberConstants.GrabberStates;
import frc.robot.Constants.WinchConstants.WinchStates;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.WinchSubsystem;

public class WinchCommand extends CommandBase {

  private final ElevatorSubsystem elevator;
  private final GrabberSubsystem grabber;
  private final WinchSubsystem winch;
  private final WinchStates winchControlState;
  private final GrabberStates grabberControlState;
  private final double motorSpeed;

  public WinchCommand(
      ElevatorSubsystem elevator, 
      GrabberSubsystem grabber, 
      WinchSubsystem winch, 
      WinchStates winchState, 
      GrabberStates grabberState, 
      double speed) {
    
    this.elevator = elevator;
    this.grabber = grabber;
    this.winch = winch;
    this.winchControlState = winchState;
    this.grabberControlState = grabberState;
    this.motorSpeed = speed;

    // Declare subsystem dependencies
    addRequirements(this.elevator, this.grabber, this.winch);
  }

  // Called when the command starts
  @Override
  public void initialize() {
    applyStateChanges();
  }

  // Abstracts the state-setting logic
  private void applyStateChanges() {
    winch.updateState(winchControlState);
    grabber.updateState(grabberControlState);
  }

  // Executes the winch movement
  @Override
  public void execute() {
    elevateWinchMotor();
  }

  // Moves the motor with the specified speed
  private void elevateWinchMotor() {
    elevator.adjustMotor(motorSpeed);
  }

  // Called when the command finishes or is interrupted
  @Override
  public void end(boolean interrupted) {
    haltMotor();
  }

  // Stops the winch motor
  private void haltMotor() {
    elevator.adjustMotor(0);
  }

  // Determines when the command should finish
  @Override
  public boolean isFinished() {
    return false;
  }
}
