// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Grabber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.GrabberSubsystem;

public class ManualGrabber extends Command {
  /** Creates a new ManualGrabber. */

  private final GrabberSubsystem m_grabber;
  private final double m_speed;

  public ManualGrabber(GrabberSubsystem grabber, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.

    m_grabber = grabber;
    m_speed = speed;
    addRequirements(grabber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_grabber.setPivot(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_grabber.setPivot(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
