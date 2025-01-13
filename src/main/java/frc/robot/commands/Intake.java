// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.GrabberSubsystem;

public class Intake extends Command {
  GrabberSubsystem m_GrabberSubsystem;
  /** Creates a new Intake. */
  public Intake(GrabberSubsystem m_GrabberSubsystem ) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_GrabberSubsystem = m_GrabberSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_GrabberSubsystem.setIntakeMotor(.1);
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
