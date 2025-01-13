// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Grabber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.Constants.GrabberConstants.GrabberStates;

public class GrabberCommand extends CommandBase {

  private final GrabberSubsystem m_grabberSubsystem;
  private final GrabberStates m_targetState;

  /** Creates a new GrabberCommand. */
  public GrabberCommand(GrabberSubsystem grabberSubsystem, GrabberStates targetState) {
    m_grabberSubsystem = grabberSubsystem;
    m_targetState = targetState;

    // Declare subsystem dependencies
    addRequirements(grabberSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Set the desired state for the grabber
    m_grabberSubsystem.updateState(m_targetState);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Apply the PID output to control the grabber pivot motor
    m_grabberSubsystem.controlPivotMotor();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stop the grabber motors when the command ends or is interrupted
    m_grabberSubsystem.controlPivotMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // The command finishes once the grabber reaches the target state
    return m_grabberSubsystem.getPivotEncoder() == m_grabberSubsystem.getDesiredAngle();
  }
}
