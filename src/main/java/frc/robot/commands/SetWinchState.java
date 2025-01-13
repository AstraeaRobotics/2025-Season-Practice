// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.fasterxml.jackson.databind.DeserializationFeature;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.WinchSubsystem;
import frc.robot.Constants.WinchConstants;
import frc.robot.Constants.WinchConstants.WinchStates;

public class SetWinchState extends Command {
  /** Creates a new SetWinchState. */
  private final WinchSubsystem m_winchSubsystem;
  private final WinchStates m_desiredState;
  public SetWinchState(WinchSubsystem m_winchSubsystem, WinchStates m_desiredState) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_desiredState = m_desiredState;
    this.m_winchSubsystem = m_winchSubsystem;
    addRequirements(m_winchSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_winchSubsystem.setState(m_desiredState);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
