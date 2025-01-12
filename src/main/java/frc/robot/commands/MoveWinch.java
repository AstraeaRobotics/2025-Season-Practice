// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class MoveWinch extends Command {
  /** Creates a new MoveWinch. */
  ElevatorSubsystem m_elevatorSubsystem;
  double speed;

  public MoveWinch(ElevatorSubsystem m_elevatorSubsystem, double speed) {
    this.m_elevatorSubsystem = m_elevatorSubsystem;
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(m_elevatorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_elevatorSubsystem.setMotorPID(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_elevatorSubsystem.setMotorPID(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}