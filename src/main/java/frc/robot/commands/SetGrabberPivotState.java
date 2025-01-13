// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.Constants.GrabberConstants;
import frc.robot.Constants.GrabberConstants.PivotStates;


public class SetGrabberPivotState extends Command {
  /** Creates a new SetGrabberState. */
  private final GrabberSubsystem m_GrabberSubsystem;
  private final PivotStates m_desiredState;
  public SetGrabberPivotState(GrabberSubsystem m_GrabberSubsystem, PivotStates m_desiredState) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_GrabberSubsystem = m_GrabberSubsystem;
    this.m_desiredState = m_desiredState;
    addRequirements(m_GrabberSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_GrabberSubsystem.setState(m_desiredState);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //pid is already in the subsystem, you justn eed to set the state
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
